public class B3 {

    private enum State {
        START, READ_CHARACTER, ESCAPE_CHARACTER, END
    }

    private State currentState = State.START;

    public void process(String input) {
        int index = 0;
        while (index < input.length()) {
            char ch = input.charAt(index);
            switch (currentState) {
                case START:
                    if (ch == '\"') {
                        currentState = State.READ_CHARACTER;
                        System.out.println("进入 READ_CHARACTER 状态");
                    }
                    index++;
                    break;

                case READ_CHARACTER:
                    if (ch == '\\') {
                        currentState = State.ESCAPE_CHARACTER;
                        System.out.println("遇到反斜杠，进入 ESCAPE_CHARACTER 状态");
                    } else if (ch == '\"') {
                        currentState = State.END;
                        System.out.println("遇到第二个双引号，进入 END 状态");
                    }
                    index++;
                    break;

                case ESCAPE_CHARACTER:
                    currentState = State.READ_CHARACTER;
                    System.out.println("跳过下一个字符，回到 READ_CHARACTER 状态");
                    index++;
                    break;

                case END:
                    System.out.println("状态机结束");
                    return;

                default:
                    throw new IllegalStateException("未知状态: " + currentState);
            }
        }
    }

    public static void main(String[] args) {
        B3 sm = new B3();
        sm.process("\"Hello \\\"World\\\"\"");
    }
}
