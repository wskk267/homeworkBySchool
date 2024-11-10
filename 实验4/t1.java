public class B1 {

    public enum State {
        SLEEP, POWER_OFF, POWER_ON, MAINTENANCE
    }

    public enum Event {
        WAKE_UP, NO_WATER, TEMPERATURE_100, WATER_BELOW_20, BED_TIME, DAMAGE
    }

    private State currentState;

    public B1() {
        this.currentState = State.SLEEP; // 初始状态为休眠
    }

    public void handleEvent(Event event) {
        switch (currentState) {
            case SLEEP:
                if (event == Event.WAKE_UP) {
                    currentState = State.POWER_OFF;
                    System.out.println("状态切换：休眠 -> 关闭烧水器电源");
                } else if (event == Event.DAMAGE) {
                    currentState = State.MAINTENANCE;
                    System.out.println("状态切换：休眠 -> 维修");
                }
                break;

            case POWER_OFF:
                if (event == Event.NO_WATER) {
                    currentState = State.SLEEP;
                    System.out.println("状态切换：关闭烧水器电源 -> 休眠");
                } else if (event == Event.WATER_BELOW_20) {
                    currentState = State.POWER_ON;
                    System.out.println("状态切换：关闭烧水器电源 -> 打开烧水器电源");
                } else if (event == Event.BED_TIME || event == Event.DAMAGE) {
                    currentState = State.MAINTENANCE;
                    System.out.println("状态切换：关闭烧水器电源 -> 维修");
                }
                break;

            case POWER_ON:
                if (event == Event.TEMPERATURE_100) {
                    currentState = State.POWER_OFF;
                    System.out.println("状态切换：打开烧水器电源 -> 关闭烧水器电源");
                } else if (event == Event.DAMAGE) {
                    currentState = State.MAINTENANCE;
                    System.out.println("状态切换：打开烧水器电源 -> 维修");
                }
                break;

            case MAINTENANCE:
                System.out.println("当前状态为维修，无法切换状态。");
                break;
        }
    }

    public State getCurrentState() {
        return currentState;
    }

    public static void main(String[] args) {
        B1 machine = new B1();

        machine.handleEvent(Event.WAKE_UP);         // 早上 7 点，唤醒
        machine.handleEvent(Event.WATER_BELOW_20);  // 有水且低于 20°
        machine.handleEvent(Event.TEMPERATURE_100); // 水温到达 100°
        machine.handleEvent(Event.BED_TIME);        // 晚上 11 点，进入维修
        machine.handleEvent(Event.NO_WATER);        // 无水返回休眠
    }
}
