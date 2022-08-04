import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int nRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int nSeatsXRow = scanner.nextInt();
        char[][] cinemaArr = new char[nRows][nSeatsXRow];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nSeatsXRow; j++) {
                cinemaArr[i][j] = 'S';
            }
        }

        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int answer = scanner.nextInt();

            switch (answer) {
                case 1:
                    showSeats(nRows, nSeatsXRow, cinemaArr);
                    break;
                case 2:
                    buyTicket(nRows, nSeatsXRow, cinemaArr);
                    break;
                case 3:
                    stats(nRows, nSeatsXRow, cinemaArr);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Answer not found!");
                    break;
            }
        } while (true);
    }

    public static void stats(int nRows, int nSeatsXRow, char[][] cinemaArr) {
        int purchasedTickets = 0;
        float percentage = 0f;
        int totalSeats = nRows * nSeatsXRow;
        int income = 0;
        int totalIncome = totalIncome(totalSeats, nRows, nSeatsXRow);

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nSeatsXRow; j++) {
                if (cinemaArr[i][j] == 'B') {
                    int rowN = i + 1;
                    purchasedTickets++;
                    percentage = ((float) purchasedTickets / totalSeats) * 100;
                    income += determinePrice(totalSeats, nRows, rowN);
                }
            }
        }
        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.printf("Current income: $%d%n", income);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    public static void showSeats(int nRows, int nSeatsXRow, char[][] cinemaArr) {
        System.out.println("Cinema:");
        for (int i = 0; i < nRows; i++) {
            if (i == 0) {
                System.out.print(" ");
                for (int j = 0; j < nSeatsXRow; j++) {
                    System.out.print(j + 1 + " ");
                }
                System.out.println();
            }
            System.out.print(i + 1 + " ");
            for (int j = 0; j < nSeatsXRow; j++) {
                System.out.print(cinemaArr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void buySeat(int nRows, int nSeatsXRow, int rowN, int seatN, char[][] cinemaArr) {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nSeatsXRow; j++) {
                if (i + 1 == rowN && j + 1 == seatN) {
                    if (cinemaArr[i][j] == 'B') {
                        System.out.println("That ticket has already been purchased!");
                        buyTicket(nRows, nSeatsXRow, cinemaArr);
                    } else {
                        cinemaArr[i][j] = 'B';
                        int totalSeats = nRows * nSeatsXRow;
                        int price = determinePrice(totalSeats, nRows, rowN);
                        System.out.println("Ticket price: $" + price);
                    }
                }
            }
        }
    }

    public static void buyTicket(int nRows, int nSeatsXRow, char[][] cinemaArr) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int rowN = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatN = scanner.nextInt();

        System.out.println();

        if (rowN > nRows || seatN > nSeatsXRow) {
            System.out.println("Wrong input!");
            buyTicket(nRows, nSeatsXRow, cinemaArr);
        } else {
            buySeat(nRows, nSeatsXRow, rowN, seatN, cinemaArr);
        }
    }

    public static int determinePrice(int totalSeats, int nRows, int rowN) {
        if (totalSeats < 60) {
            return 10;
        } else {
            if (nRows % 2 == 0) {
                int half = nRows / 2;
                if (rowN > half) {
                    return 8;
                } else {
                    return 10;
                }
            } else {
                int secondHalf = Math.round((float) nRows / 2);
                if (rowN < secondHalf) {
                    return 10;
                } else {
                    return 8;
                }
            }
        }
    }

    public static int totalIncome(int totalSeats, int nRows, int nSeatsXRow) {
        if (totalSeats < 60) {
            return 10 * totalSeats;
        } else {
            if (nRows % 2 == 0) {
                int half = nRows / 2;
                int firstHalf = half * 10 * nSeatsXRow;
                int secondHalf = half * 8 * nSeatsXRow;
                return firstHalf + secondHalf;
            } else {
                int secondHalf = Math.round((float) nRows / 2);
                int firstHalf = secondHalf - 1;
                int priceFirstHalf = firstHalf * nSeatsXRow * 10;
                int priceSecondHalf = secondHalf * nSeatsXRow * 8;
                return priceFirstHalf + priceSecondHalf;
            }
        }
    }
}
