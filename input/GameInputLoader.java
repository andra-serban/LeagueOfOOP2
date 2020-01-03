package input;


import angels.Angel;
import angels.AngelFactory;
import constants.Constants;
import fileio.FileSystem;
import map.Map;
import player.Player;
import player.PlayerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class GameInputLoader {
    private final String mInputPath;
    private final String mOutputPath;

    public GameInputLoader(final String inputPath, final String outputPath) {
        mInputPath = inputPath;
        mOutputPath = outputPath;
    }

    public GameInput load() throws IOException {

        int length = 0;
        int width = 0;
        char[][] directions = new char[Constants.MAXIM_NR_MOVES][];
        List<String> groundType = new ArrayList<>();
        int playerTypeNr = 0;
        int roundsNr = 0;
        List<Player> players = new ArrayList<>();
        ArrayList<ArrayList<Angel>> angels = new ArrayList<>();
        List<String> directionsList = new ArrayList<>();
        Player[][] position = new Player[0][];

        try {
            FileSystem fs = new FileSystem(mInputPath, mOutputPath);
            length = fs.nextInt();
            width = fs.nextInt();
            for (int i = 0; i < length; i++) {
                groundType.add(fs.nextWord());
            }

            playerTypeNr = fs.nextInt();
            position = new Player[length][width];
            PlayerFactory playerFactory = PlayerFactory.getInstance();

            /*
            citirea coordonatelor si a tipului fiecarui player
             */
            for (int i = 0; i < playerTypeNr; ++i) {
                String type = fs.nextWord();
                int x = fs.nextInt();
                int y = fs.nextInt();
                players.add(playerFactory.createPlayer(type, i, x, y));
                position[x][y] = players.get(players.size() - 1);

            }
            Map map = Map.getInstance(groundType, length, width, position);

            /*
            citirea directiilor pentru fiecare runda
             */
            roundsNr = fs.nextInt();
            for (int i = 0; i < roundsNr; ++i) {
                directionsList.add(fs.nextWord());
            }
            String[] array = directionsList.toArray(new String[0]);
            directions = new char[roundsNr][playerTypeNr];
            for (int i = 0; i < array.length; ++i) {
                char[] typeList = array[i].toCharArray();
                for (int j = 0; j < typeList.length; ++j) {
                    directions[i][j] = typeList[j];
                }
            }

            /*
            citirea coordonatelor si a tipului fiecarui inger
             */
            AngelFactory angelFactory = AngelFactory.getInstance();
            for  (int i = 0; i < roundsNr; i++) {
                int nrAngels = fs.nextInt();
                ArrayList<Angel> angelList = new ArrayList<>();
                if (nrAngels != 0) {
                    for (int j = 0; j < nrAngels; j++) {
                        String angel = fs.nextWord();
                        String[] str = angel.split(",", 10);
                        String type = str[0];
                        int x = Integer.parseInt(str[1]);
                        int y = Integer.parseInt(str[2]);
                        angelList.add(angelFactory.createAngel(type, i, x, y));

                    }
                    angels.add(angelList);
                } else {
                    angels.add(null);
                }
            }

            fs.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return  new GameInput(groundType, players,
                position, roundsNr, directions, angels);
    }

}
