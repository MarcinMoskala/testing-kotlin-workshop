import java.math.BigDecimal

fun fib(num: Int): BigDecimal {
    require(num >= 0) { "Cannot calculate Fibonacci number for number smaller than 0, it is $num" }
    return (0 until num).fold(BigDecimal.ZERO to BigDecimal.ONE) { (a, b), i -> b to (a + b) }.first
}