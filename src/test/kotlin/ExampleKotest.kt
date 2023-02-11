import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith

class FunSpecTests : FunSpec({
    // before...
    // context
    test("String length should return the length of the string") {
        "sammy".length shouldBe 5
        "".length shouldBe 0
    }
})

class StringSpecTests : StringSpec({
    "strings.length should return size of string" {
        "hello".length shouldBe 5
    }
})

class ShouldSpecTests : ShouldSpec({
    should("return the length of the string") {
        "sammy".length shouldBe 5
        "".length shouldBe 0
    }
})

class DescribeSpecTests : DescribeSpec({
    describe("score") {
        it("start as zero") {
            // test here
        }
        describe("with a strike") {
            it("adds ten") {
                // test here
            }
            it("carries strike to the next frame") {
                // test here
            }
        }

        describe("for the opposite team") {
            it("Should negate one score") {
                // test here
            }
        }
    }
})

class BehaviorSpecTests : BehaviorSpec({
    given("a broomstick") {
        `when`("I sit on it") {
            then("I should be able to fly") {
                // test code
            }
        }
        `when`("I throw it away") {
            then("it should come back") {
                // test code
            }
        }
    }
})

class WordSpecTests : WordSpec({
    "String.length" should {
        "return the length of the string" {
            "sammy".length shouldBe 5
            "".length shouldBe 0
        }
    }
})