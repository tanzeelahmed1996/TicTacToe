import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();


    public static void main(String[] args) {

        char [][] gameBoard = {{' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}};


        while (true){
            Scanner sc = new Scanner (System.in);
            System.out.println("Insert your preferred position (1-9): ");
            int playerPosition = sc.nextInt();

            while (playerPositions.contains(playerPosition)||cpuPositions.contains(playerPositions)){ // loop checks if player positions are already taken
                System.out.println("Position taken! Try another position");
                playerPosition = sc.nextInt();
            }
            positionOnBoard(gameBoard,playerPosition,"p1");
            String result = checkWinner();
            if (result.length()>0){
                printBoard(gameBoard);
                System.out.println(result);
                break;
            }


            Random rand = new Random();
            int cpuPos = rand.nextInt(9)+1;
            while (playerPositions.contains(cpuPos)||cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9)+1;
            }
            positionOnBoard(gameBoard,cpuPos,"cpu");

            printBoard(gameBoard);
            result = checkWinner();
            if (result.length()>0){
                printBoard(gameBoard);
                System.out.println(result);
                break;
            }

        }

    }

    /***
     * Method to print the board
     * @param gameBoard
     */
    public static void printBoard(char [][] gameBoard){

        for (int i=0; i<gameBoard.length; i++){
            System.out.print(gameBoard[i]);
            System.out.println();
        }
    } // method to print the board

    /***
     * Method that updates the positions. So can keep track of what positions are filled.
     * @param gameBoard
     * @param position
     * @param user
     */
    public static void positionOnBoard (char [][] gameBoard, int position, String user){
        char symbol = ' ';
        if (user.equals("p1")){
            symbol = 'X';
            playerPositions.add(position);
        }else if(user.equals("cpu")){
            symbol= 'O';
            cpuPositions.add(position);
        }
        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;

        }
    }

    /***
     * Method contains the winning conditions and checks who has won.
     * @return
     */
    public static String checkWinner (){

        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);

        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);

        List diag1= Arrays.asList(1,5,9);
        List diag2 = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(diag1);
        winning.add(diag2);

        for (List l:winning){
            if (playerPositions.containsAll(l)){
                return "You won!";
            }else if(cpuPositions.containsAll(l)){
                return "CPU won!";
            }else if (playerPositions.size()+cpuPositions.size()==9){
                return "TIE!";
            }
        }
        return "";
    }
}
