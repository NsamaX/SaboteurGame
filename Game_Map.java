class Game_Map {
    // Create a map
    private int row = 21, col = row;
    private char[][] paths = new char[row][col];
    private int row_chest = row/2, col_chest = (col/2) + 7;
    Game_Map() {
        /*
        - = null
        n = none
        s = surveyed
        g = can go
        b = block
        c = chest
        */
        // Initialize the path with -
        for (int i=0;i<row;i++) {
            for (int j=0;j<col;j++) {
                paths[i][j] = '-';
            }
        }
        // Can go in all directions from the starting point
        paths[row/2][col/2] = 's';
        paths[(row/2)-1][col/2] = 'g';
        paths[(row/2)+1][col/2] = 'g';
        paths[row/2][(col/2)+1] = 'g';
        paths[row/2][(col/2)-1] = 'g';
        // Chest is seven spaces away from the starting point
        paths[row_chest][col_chest] = 'c';
        paths[row_chest + 2][col_chest] = 'c';
        paths[row_chest - 2][col_chest] = 'c';
    }
    /* 
    Route Code The first set of letters is the possible route. 
    The second set of letters is the impossible path.
    l = left
    r = Right
    t = top
    d = down
    */
    private String[] routes = {
        "n-n",
        "n-glrtd",
        "lt-brd",
        "rt-bld",
        "td-btd",
        "r-bl",
        "t-bd",
        "ltd-brtd",
        "lrtd-blrtd",
        "lr-blr",
        "lrd-blrt",
        "lr-glr",
        "lrt-glrd",
        "lt-grd",
        "rt-gld",
        "td-gtd",
        "rtd-gltd",
        "lrtd-glrtd",
        "lt-brd",
        "lrtd-blrtd",
        "rt-bld"
    };
    public boolean checkPath(char srow, char scol) {
        int row = (int)srow - 65;
        int col = (int)scol - 65;
        if(paths[row][col] == 'g'){
            return true;
        }
        return false;
    }
    public void setPath(int row, int col,  int route) {
        // If the chosen route can be taken check the conditions.
        String[] path = routes[route].split("-");
        System.out.println(routes[route]);
        boolean push = false;
        for(int i=0;i<path[0].length();i++){
            if(path[0].charAt(i) == 'l' && paths[row][col+1] == 's'){
                push = true;
            }
            else if(path[0].charAt(i) == 'r' && paths[row][col-1] == 's'){
                push = true;
            }
            else if(path[0].charAt(i) == 't' && paths[row+1][col] == 's'){
                push = true;
            }
            else if(path[0].charAt(i) == 'd' && paths[row-1][col] == 's'){
                push = true;
            }
        }
        if(push){
            // Show path can go
            for(int j=0;j<path[1].length();j++){
                if(path[1].charAt(j) == 'l' && paths[row][col-1] == '-'){
                    paths[row][col - 1] = 'g';
                }
                if(path[1].charAt(j) == 'r' && paths[row][col+1] == '-'){
                    paths[row][col + 1] = 'g';
                }
                if(path[1].charAt(j) == 't' && paths[row-1][col] == '-'){
                    paths[row - 1][col] = 'g';
                }
                if(path[1].charAt(j) == 'd' && paths[row+1][col] == '-'){
                    paths[row + 1][col] = 'g';
                }
            }
            // Show path block
            if(path[1].charAt(0) == 'g'){
                paths[row][col] = 's';
            } else {
                paths[row][col] = 'b';
            }
            System.out.println("success");
            endGame();
        } else {
            System.out.println("wrong path");
        }
    }
    // Check chest
    private void endGame(){
        // To do
    }
    // Show map
    public void showMap() {
        System.out.print("\n  ");
        int c = 65;
        for(int i = 0;i < col; i++){
            System.out.print((char)c + " ");
            c ++;
        }
        c = 65;
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print((char)c + " ");
            c ++;
            for (int j = 0; j < col; j++) {
                System.out.print(paths[i][j] + " ");
            }
            System.out.println();
        }
    }
}
