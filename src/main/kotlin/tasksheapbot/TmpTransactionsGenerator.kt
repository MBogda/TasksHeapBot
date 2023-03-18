package tasksheapbot

import com.soywiz.klock.DateTime
import java.math.BigDecimal

class TmpTransactionsGenerator {
    fun generate(number: Int = 10): List<TmpTransaction> {
        for (i in 0 until number) {
            // todo
        }
        return emptyList<TmpTransaction>()
    }
}

data class TmpTransaction(
    val name: String,
    val amount: BigDecimal,
    val currency: String,
    val currencySign: Char,
    val text: String,
    val date: DateTime,
)
