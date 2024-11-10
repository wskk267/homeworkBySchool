public class B2 {

    public enum State {
        IDLE, READ, VALIDATE_PASSWORD, LOCKED, SELECTION
    }

    public enum Event {
        KEY_PRESS, ENTER_PASSWORD, PASSWORD_CORRECT, PASSWORD_INCORRECT, TIMER_EXPIRED, ACTIVATION_SUCCESS
    }

    private State currentState;
    private int retryCount;
    private long timerStart;

    public B2() {
        this.currentState = State.IDLE;
        this.retryCount = 0;
    }

    public void handleEvent(Event event) {
        switch (currentState) {
            case IDLE:
                if (event == Event.KEY_PRESS) {
                    startTimer();
                    currentState = State.READ;
                    System.out.println("状态切换：休眠 -> 读取");
                }
                break;

            case READ:
                if (event == Event.TIMER_EXPIRED && timerExceeded(120)) {
                    currentState = State.IDLE;
                    System.out.println("状态切换：读取 -> 休眠（超时）");
                } else if (event == Event.ENTER_PASSWORD) {
                    currentState = State.VALIDATE_PASSWORD;
                    System.out.println("状态切换：读取 -> 比较4位密码");
                }
                break;

            case VALIDATE_PASSWORD:
                if (event == Event.PASSWORD_CORRECT) {
                    currentState = State.SELECTION;
                    System.out.println("状态切换：比较4位密码 -> 选择（密码正确）");
                } else if (event == Event.PASSWORD_INCORRECT) {
                    retryCount++;
                    if (retryCount > 3) {
                        currentState = State.LOCKED;
                        startTimer();
                        System.out.println("状态切换：比较4位密码 -> 锁定（重试次数超过3）");
                    } else {
                        System.out.println("密码不正确，重试次数：" + retryCount);
                    }
                } else if (event == Event.ACTIVATION_SUCCESS) {
                    currentState = State.SELECTION;
                    System.out.println("状态切换：比较4位密码 -> 选择（激活成功）");
                }
                break;

            case LOCKED:
                if (event == Event.TIMER_EXPIRED && timerExceeded(120)) {
                    retryCount = 0;
                    currentState = State.IDLE;
                    System.out.println("状态切换：锁定 -> 休眠（超时）");
                }
                break;

            case SELECTION:
                System.out.println("当前状态为选择，进入功能选择界面。");
                break;
        }
    }

    private void startTimer() {
        this.timerStart = System.currentTimeMillis();
    }

    private boolean timerExceeded(int seconds) {
        long currentTime = System.currentTimeMillis();
        return (currentTime - timerStart) / 1000 >= seconds;
    }

    public State getCurrentState() {
        return currentState;
    }

    public static void main(String[] args) {
        B2 machine = new B2();

        machine.handleEvent(Event.KEY_PRESS);           // 键盘敲击，进入读取状态
        machine.handleEvent(Event.ENTER_PASSWORD);      // 输入密码
        machine.handleEvent(Event.PASSWORD_INCORRECT);  // 密码不正确
        machine.handleEvent(Event.PASSWORD_INCORRECT);  // 密码不正确
        machine.handleEvent(Event.PASSWORD_INCORRECT);  // 密码不正确，进入锁定状态
        machine.handleEvent(Event.TIMER_EXPIRED);       // 超时解除锁定
        machine.handleEvent(Event.KEY_PRESS);           // 键盘敲击，重新进入读取状态
        machine.handleEvent(Event.ENTER_PASSWORD);      // 输入密码
        machine.handleEvent(Event.PASSWORD_CORRECT);    // 密码正确，进入选择状态
    }
}
