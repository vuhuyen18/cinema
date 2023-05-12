import java.util.*
//import java.text.DecimalFormat
fun main() {
    println("Enter the number of rows: ")
    print("> ")
    val numRows = readLine()?.toIntOrNull() ?: return

    println("Enter the number of seats in each row: ")
    print("> ")
    val numCols = readLine()?.toIntOrNull() ?: return

    val cinema = Array(numRows) { BooleanArray(numCols) }
    val prices = Array(numRows) { row -> IntArray(numCols) { col -> getSPrice(row, col, numRows, numCols) } }

    var ticketSold = 0   //luu tru so luong ve da ban
    var totalSeat = numRows * numCols   //tong so ghe co trong rap
    var currentIncome = 0  // thu nhap hien tai ban ve
    //tong thu nhap neu ban het ve
    var totalIncome = if(totalSeat <=60)totalSeat* 10 else (numRows /2)*numCols*10+((numRows+1)/2)*numCols*8



    while (true) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")

        //nhap so theo dk
        print(">")
        val a = readln().toInt()
        when (a) {
            1 -> {

                println("Cinema:")

                print("  ")
                for (col in 1..numCols) {
                    print("$col ")
                }
                println()

                for (row in 0 until numRows) {
                    print("${row + 1} ")
                    for (col in 0 until numCols) {
                        print(if (cinema[row][col]) "B " else "S ")
                    }
                    println()
                }
            }

            2 -> {
                while (true) {
                    println("Enter a row number: ")
                    print("> ")
                    val row = readLine()?.toIntOrNull()?.let { it - 1 } ?: return
                 /*   if (row < 0 || row >= numRows) {
                        println("Wrong input!")
                        continue
                    }*/

                    println("Enter a seat number in that row: ")
                    print("> ")
                    val col = readLine()?.toIntOrNull()?.let { it - 1 } ?: return
                 /*   if (col < 0 || col >= numCols) {
                        println("Wrong input!")
                        continue
                    }*/
                    if((row < 0 || row >= numRows)||(col < 0 || col >= numCols)){
                        println("Wrong input")
                        continue
                    }
                    if (cinema[row][col]) {
                        println("That ticket has already been purchased!")
                      //  continue
                    }else {

                        cinema[row][col] = true
                        ticketSold++
                        currentIncome += prices[row][col]
                        val price = prices[row][col]
                        println("Ticket price: \$$price")
                        break
                    }
                }
            }
            3 -> {
                //hien thi phan tram
                var percen = (ticketSold.toFloat()/totalSeat)*100
                var formatpercen = "%.2f".format(percen)
                //hien thi

                println("Number of purchased tickets: $ticketSold")
                println("Percentage :$formatpercen % ")
                println("Current income: $$currentIncome")
                println("Total income: $$totalIncome")
            }

            0 -> return
        }
    }
}
fun getSPrice(row: Int, col: Int, numRows: Int, numCols: Int): Int {
    return if (numRows * numCols <= 60) {
        10
    } else {
        val frontHalf = numRows / 2
        if (row < frontHalf) 10 else 8
    }

}
