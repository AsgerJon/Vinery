package satisfyu.vinery.block.stem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import satisfyu.vinery.block.grape.GrapeProperty;
import satisfyu.vinery.block.grape.GrapeType;
import satisfyu.vinery.item.GrapeBushSeedItem;
import satisfyu.vinery.registry.GrapeTypeRegistry;
import satisfyu.vinery.util.ConnectingProperties;
import satisfyu.vinery.util.LineConnectingType;

public class NewLatticeBlock extends Block implements SimpleWaterloggedBlock, BonemealableBlock {
    private static final int MAX_AGE = 4;
    private static final float BREAK_SOUND_PITCH = 0.8F;

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty SUPPORT = BooleanProperty.create("support");
    public static final GrapeProperty GRAPE = GrapeProperty.create("grape");
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    protected static final VoxelShape EAST = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH = Block.box(0.0D, 0.0D, 0.01D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape NORTH = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<LineConnectingType> TYPE = ConnectingProperties.VINERY_LINE_CONNECTING_TYPE;

    private static final SoundEvent BREAK_SOUND_EVENT = SoundEvents.SWEET_BERRY_BUSH_BREAK;
    private static final SoundEvent PLACE_SOUND_EVENT = SoundEvents.SWEET_BERRY_BUSH_PLACE;
    private static final SoundEvent PICK_BERRIES_SOUND_EVENT = SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES;

    public NewLatticeBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH)
                .setValue(SUPPORT, true)
                .setValue(GRAPE, GrapeTypeRegistry.NONE)
                .setValue(AGE, 0)
                .setValue(TYPE, LineConnectingType.NONE));
    }

    public boolean isMature(BlockState state) {
        return state.getValue(AGE) >= MAX_AGE;
    }

    public BlockState withAge(BlockState state, int age, GrapeType type) {
        return state.setValue(AGE, age).setValue(GRAPE, type);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        Direction facing = context.getHorizontalDirection().getOpposite();

        BlockPos clickedPos = context.getClickedPos();
        Direction clickedFace = context.getClickedFace();
        BlockPos clickedFacingPos = clickedPos.relative(clickedFace.getOpposite());
        BlockState clickedFacingState = level.getBlockState(clickedFacingPos);

        if (context.getPlayer() != null && !context.getPlayer().isCrouching() && clickedFacingState.getBlock() instanceof NewLatticeBlock) {
            Direction clickedFacingFace = clickedFacingState.getValue(FACING);
            if (clickedFacingFace != clickedFace && clickedFacingFace.getOpposite() != clickedFace) facing = clickedFacingFace;
        }
        BlockState state = getConnection(this.defaultBlockState().setValue(FACING, facing), level, clickedPos);
        return state.setValue(WATERLOGGED, level.getFluidState(clickedPos).getType() == Fluids.WATER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case WEST -> WEST;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            default -> NORTH;
        };
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide && player.getItemInHand(hand).getItem() instanceof AxeItem) {
            BlockState newState = state.setValue(SUPPORT, !state.getValue(SUPPORT));
            BlockState updateState = getConnection(newState, world, pos);
            world.setBlock(pos, updateState, 3);
            return InteractionResult.SUCCESS;
        }
        final ItemStack stack = player.getItemInHand(hand);
        final int age = state.getValue(AGE);

        if (hand == InteractionHand.OFF_HAND) {
            return super.use(state, world, pos, player, hand, hit);
        }

        if (age > 0 && stack.getItem() == Items.SHEARS) {
            stack.hurtAndBreak(1, player, player2 -> player2.broadcastBreakEvent(player.getUsedItemHand()));
            if (age > 2) {
                dropGrapes(world, state, pos);
            }
            dropGrapeSeeds(world, state, pos);
            world.setBlock(pos, state.setValue(AGE, 0), 3);
            world.playSound(player, pos, BREAK_SOUND_EVENT, SoundSource.AMBIENT, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        else if (stack.getItem() instanceof GrapeBushSeedItem seed && seed.getType().isLattice() && age == 0) {
            world.setBlock(pos, withAge(state, 1, seed.getType()), 3);
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            world.playSound(player, pos, PLACE_SOUND_EVENT, SoundSource.AMBIENT, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        else if (age > 2) {
            stack.hurtAndBreak(1, player, player2 -> player2.broadcastBreakEvent(player.getUsedItemHand()));
            dropGrapes(world, state, pos);
            world.setBlock(pos, state.setValue(AGE, 1), 3);
            world.playSound(player, pos, BREAK_SOUND_EVENT, SoundSource.AMBIENT, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }

        return super.use(state, world, pos, player, hand, hit);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!isMature(state) && state.getValue(AGE) > 0) {
            final int i;
            if (world.getRawBrightness(pos, 0) >= 9 && (i = state.getValue(AGE)) < MAX_AGE) {
                world.setBlock(pos, this.withAge(state,i + 1, state.getValue(GRAPE)), Block.UPDATE_CLIENTS);
            }
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(world, pos)) {
            if (state.getValue(AGE) > 0) {
                dropGrapeSeeds(world, state, pos);
            }
            if (state.getValue(AGE) > 2) {
                dropGrapes(world, state, pos);
            }
            world.destroyBlock(pos, true);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        return getConnection(state, level, currentPos);
    }

    public BlockState getConnection(BlockState state, LevelAccessor level, BlockPos currentPos) {
        Direction facing = state.getValue(FACING);

        BlockState stateL = level.getBlockState(currentPos.relative(facing.getClockWise()));
        BlockState stateR = level.getBlockState(currentPos.relative(facing.getCounterClockWise()));

        boolean sideL = (stateL.getBlock() instanceof NewLatticeBlock && (stateL.getValue(FACING) == facing || stateL.getValue(FACING) == facing.getClockWise()));
        boolean sideR = (stateR.getBlock() instanceof NewLatticeBlock && (stateR.getValue(FACING) == facing || stateR.getValue(FACING) == facing.getCounterClockWise()));
        LineConnectingType type = sideL && sideR ?
                LineConnectingType.MIDDLE
                : (sideR ? LineConnectingType.LEFT
                : (sideL ? LineConnectingType.RIGHT
                : LineConnectingType.NONE));

        if (!state.getValue(SUPPORT)) type = LineConnectingType.MIDDLE;

        return state.setValue(TYPE, type);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED, SUPPORT, AGE, GRAPE);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public void dropGrapes(Level world, BlockState state, BlockPos pos) {
        final int x = 1 + world.random.nextInt(isMature(state) ? 2 : 1);
        final int bonus = isMature(state) ? 2 : 1;
        Item grape = state.getValue(GRAPE).getFruit();
        popResource(world, pos, new ItemStack(grape, x + bonus));
        world.playSound(null, pos, PICK_BERRIES_SOUND_EVENT, SoundSource.BLOCKS, 1.0F, BREAK_SOUND_PITCH + world.random.nextFloat() * 0.4F);
    }

    public void dropGrapeSeeds(Level world, BlockState state, BlockPos pos) {
        Item grape = state.getValue(GRAPE).getSeeds();
        popResource(world, pos, new ItemStack(grape));
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if (state.getValue(AGE) > 2) {
            dropGrapes(world, state, pos);
        }
        super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState state, boolean bl) {
        return !isMature(state) && levelReader.getBlockState(blockPos.below()).getBlock() == this && state.getValue(AGE) > 0;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        boneMealGrow(world, state, pos);
    }

    private void boneMealGrow(Level world, BlockState state, BlockPos pos) {
        int j;
        int age = state.getValue(AGE) + Mth.nextInt(world.getRandom(), 1, 2);
        if (age > (j = MAX_AGE)) {
            age = j;
        }
        world.setBlock(pos, this.withAge(state, age, state.getValue(GRAPE)), Block.UPDATE_CLIENTS);
    }
}
