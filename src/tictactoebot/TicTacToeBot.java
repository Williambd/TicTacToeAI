/*
 * Created By WilliamBD
 * to evolve an AI that can play tic tac toe.
 * on 19/06/2018
 */
package tictactoebot;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author widea9928
 */
public class TicTacToeBot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //generate individuals
        ArrayList<Individual> population = new ArrayList();

        //temp population
        ArrayList<Individual> tempPopulation = new ArrayList();

        //create 1000 new population
        for (int i = 0; i < 500; i++) {
            population.add(i, new Individual());
            population.get(i).randomID();
        }

        //generate 2 networks
        Network net1 = new Network();
        Network net2 = new Network();

        //gameBoards are generated
        GameBoard game1 = new GameBoard();
        GameBoard game2 = new GameBoard();
        for (int rounds = 0; rounds < 1000; rounds++) {
            //for counting game number
            int gameNum = 0;
            for (int i = 0; i < population.size(); i++) {
                //assigns the individual as player 1
                net1.assignIndividual(population.get(i));

                for (int j = 0; j < population.size(); j++) {
                    //plays every other individual
                    net2.assignIndividual(population.get(j));

                    //initialises the board
                    game1.init();
                    game2.init();

                    //AND THE GAME BEGINS!~!~!~!
                    for (int k = 0; k < 30; k++) {
                        //run player1
                        net1.updateBoard(game1.board);
                        net1.excecuteNetwork();
                        game1.MyMove(net1.outputMove());
                        game2.OponentMove(net1.outputMove());

                        //check for win
                        if (game1.winCheck() == 1) {
                            //givesPoints, then ends game.
                            net1.givePoints(1);
                            break;
                        }

                        //run player2
                        net2.updateBoard(game2.board);
                        net2.excecuteNetwork();
                        game2.MyMove(net2.outputMove());
                        game1.OponentMove(net2.outputMove());

                        //check for win or tie
                        if (game1.winCheck() == -1) {
                            //givesPoints then ends game
                            net2.givePoints(1);
                            break;
                        } else if (game1.winCheck() == 3) {
                            break;
                        }

                    }
                    //tells network that the games are complete
                    net1.gameComplete();
                    net2.gameComplete();
                    gameNum += 1;
                }
            }

            //sort population
            Collections.sort(population);
            
            //print win best player win count
            System.out.println("round: "+rounds);
            System.out.println("top Score: "+population.get(0).rawScore);
            
            //calculates average win
            double averageWin=0;
            for (int i = 0; i < population.size(); i++) {
                averageWin += population.get(i).rawScore;
            }
            averageWin = averageWin/population.size();
            
            //prints average wins and round number
            System.out.println("average wins: "+averageWin);
            int increment = 0;
            for (int i=0; i<25; i++){
                //for each of the top 25 players, 19 mutants are created based on each one. also clears all players points;
                population.get(i).clearPoints();
                for (int j=0; j<19; j++){
                    population.get(increment+25).setId(population.get(i).id);
                    population.get(increment+25).mutate();
                    population.get(increment+25).clearPoints();
                    increment++;
                }
            }
            
        }
    }
}
