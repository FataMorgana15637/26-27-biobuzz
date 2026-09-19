package utility.actionBase.logging;

public interface SliceContainer extends Slice {
    Slice getSlice();

    @Override
    default boolean isEnabled() {
        return getSlice().isEnabled();
    }

    @Override
    default void start() {
        getSlice().start();
    }

    @Override
    default void complete() {
        getSlice().complete();
    }

    @Override
    default long getStartTime() {
        return getSlice().getStartTime();
    }

    @Override
    default long getEndTime() {
        return getSlice().getEndTime();
    }

    @Override
    default long getRunningTime() {
        return getSlice().getRunningTime();
    }

    @Override
    default String getName(){
        return getSlice().getName();
    }


}
