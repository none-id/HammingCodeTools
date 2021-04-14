package prepare;

import java.lang.*;

public class PendingC {
    private boolean[][] map = new boolean[10][55];

    public PendingC() {
        for (int i = 1; i <= 50; i++) {
            String temp = Integer.toBinaryString(i);

            for (int j = temp.length() - 1; j >= 0; j--) {
                if (temp.charAt(j) == '1') {
                    map[temp.length() - j][i] = true;
                }
            }
        }

    }

    public boolean[] getCGroup(int Cvalue) {
        return map[Cvalue];
    }
}
