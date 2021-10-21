package logic;

public abstract class BaseLogic {

    abstract void start();

    public void execute() {
        start();
        do {
            processLoop();
        } while (!isEnd());
        finish();
    }

    protected abstract boolean isEnd();

    protected abstract void finish();

    protected abstract void processLoop();

}
