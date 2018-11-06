package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Main {


        static Statement statement;

        public static void main (String[]args) throws IOException, SQLException {

            connectionDBandReadTXT();
            distinctSongs_346();
            maxDistinctSongs();

        }

        private static void maxDistinctSongs () {

            try {
                ResultSet maxNumberOfDistinctSongs = statement.executeQuery("select count (distinct SONG_ID) from exhibit");
                int number = maxNumberOfDistinctSongs.getInt(1);
                System.out.println("Maximum Distinct Sons are : " + number);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        public static void distinctSongs_346 () throws SQLException {

            ResultSet max_distinctSongs = statement.executeQuery("select count (distinct SONG_ID) from exhibit");

            int clientNumber = 0;
            for (int i = 1; i <= max_distinctSongs.getInt(1); i++) {

                ResultSet users_346 = statement.executeQuery("select distinct SONG_ID from exhibit where CLIENT_ID='" + i + "'");
                if (users_346.getInt(1) == 346) {
                    clientNumber++;
                }

            }
            System.out.println(clientNumber + " users played distinct songs");

        }

        public static void connectionDBandReadTXT () throws SQLException, IOException {
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/JavaProjects/DenemePIworks/test.db")) {

                statement = conn.createStatement();
                BufferedReader br = new BufferedReader(new FileReader("input.txt"));
                String line;

                while ((line = br.readLine()) != null) {
                    String[] value = line.split("\t");
                    statement.execute("insert into exhibit (PLAY_ID,SONG_ID,CLIENT_ID,PLAY_TS) values('" + value[0] + "','" + value[1] + "','" + value[2] + "','" + value[3] + "')");
                }

                br.close();
                conn.close();
                statement.close();

            }
        }
    }



