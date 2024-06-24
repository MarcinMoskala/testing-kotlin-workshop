import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.Test

class AssertJExamples {
    data class Character(val name: String, val profession: String)

    val geralt = Character("Geralt", "Witcher")
    val dandelion = Character("Dandelion", "Bard")
    val triss = Character("Triss", "Wizard")
    val nonCharacter = Character("XXX", "YYY")
    val characters = listOf(geralt, dandelion, triss)

    @Test
    fun comparisons() {
        assertThat(geralt.name).isEqualTo("Geralt")
        assertThat(geralt).isNotEqualTo(dandelion)
    }

    @Test
    fun strings() {
        assertThat(triss.name)
            .hasSize(5)
            .startsWith("Tr")
            .endsWith("iss")
    }

    @Test
    fun collections() {
        assertThat(characters)
            .hasSize(3)
            .contains(geralt, dandelion, triss)
            .doesNotContain(nonCharacter)

        assertThat(characters).containsExactly(geralt, dandelion, triss) // Order matters
        assertThat(characters).containsOnly(triss, dandelion, geralt) // Order does not matter
        assertThat(characters).containsOnlyOnce(triss)
        assertThat(characters).containsAll(listOf(dandelion, triss))
        assertThat(characters).containsAnyOf(nonCharacter, triss)

        assertThat(characters)
            .filteredOn { it.profession == "Bard" }
            .hasSize(1)
            .containsExactly(dandelion)
    }

    @Test
    fun extracting() {
        assertThat(characters)
            .extracting("name", "profession")
            .contains(
                tuple("Geralt", "Witcher"),
                tuple("Dandelion", "Bard"),
                tuple("Triss", "Wizard")
            )
    }
}
