public class GameOfLife {
    public void gameOfLifeImplementation(){
    int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
    int [][] newBoard = new int[board.length][board[0].length];
        for(int i = 0;i<board.length;i++) {
        for(int j = 0;j<board[i].length;j++){
            int counter = getCount(board,i,j);
            if(board[i][j]==0 && counter == 3){
                newBoard[i][j]=1;
                continue;
            }
            if(board[i][j]==1 && counter<2){
                newBoard[i][j]=0;
                continue;
            }
            if(board[i][j]==1 && (counter==2 || counter == 3)){
                newBoard[i][j]=1;
                continue;
            }
            if(board[i][j]==1 && counter > 3){
                newBoard[i][j]=0;
                continue;
            }
            newBoard[i][j]=board[i][j];
        }
    }
        for(int i = 0;i<board.length;i++) {
        for(int j = 0;j<board[i].length;j++){
            board[i][j]=newBoard[i][j];
        }
    }
}

private static int getCount(int[][] board, int i, int j) {
    int counter = 0;
    if(isLeftInbound(board, i,j)&&board[i][j-1]==1){
        System.out.println("west hit for "+i+" "+j );
        counter++;
    }
    if(isRightInbound(board, i,j)&&board[i][j+1]==1){
        System.out.println("east hit for "+i+" "+j );
        counter++;
    }
    if(isUpInbound(board, i, j)){
        System.out.println("north is "+board[i-1][j]);
    }

    if(isUpInbound(board, i, j)&&board[i-1][j]==1){
        System.out.println("north hit for "+i+" "+j);
        counter++;
    }
    if(isDownInbound(board, i, j)&&board[i+1][j]==1){
        System.out.println("south hit for "+i+" "+j );
        counter++;
    }
    if(isLeftInbound(board, i,j)&&isUpInbound(board, i, j)&&board[i-1][j-1]==1){
        System.out.println("northwest hit for "+i+" "+j );
        counter++;
    }
    if(isRightInbound(board, i,j)&&isUpInbound(board, i, j)&&board[i-1][j+1]==1){
        System.out.println("northeast hit for "+i+" "+j );
        counter++;
    }
    if(isRightInbound(board, i,j)&&isDownInbound(board, i, j)&&board[i+1][j+1]==1){
        System.out.println("southeast for "+i+" "+j );
        counter++;
    }
    if(isLeftInbound(board, i,j)&&isDownInbound(board, i, j)&&board[i+1][j-1]==1){
        System.out.println("southwest hit for "+i+" "+j );
        counter++;
    }
    System.out.println(
            i+"-"+j
                    +" Left "+isLeftInbound(board,i,j)
                    +" Right "+isRightInbound(board,i,j)
                    +" Up "+isUpInbound(board,i,j)
                    +" Down "+isDownInbound(board,i,j)
                    +" Counter = "+counter);
    return counter;
}
private static boolean isLeftInbound(int[][] board, int i, int j){
    if(j>0){
        return true;
    }
    return false;
}
private static boolean isRightInbound(int[][] board, int i, int j){
    if(j<board[i].length-1){
        return true;
    }
    return false;
}
private static boolean isUpInbound(int[][] board, int i, int j){
    if(i>0){
        return true;
    }
    return false;
}
private static boolean isDownInbound(int[][] board, int i, int j){
    if(i<board.length-1){
        return true;
    }
    return false;
}
}
