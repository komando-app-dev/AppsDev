package tomerbu.edu.lec21kotlin

/**
Movie, Person, Student
 */
data class Pokemon(val name: String, val hitPoints: Double = 0.0, val credits: Int = 0)

//toString, getters, setters,copy, hash, equals


data class Person(val firstName: String, val lastName: String) {
    //func getFullName
}

//classes and data classes. firebase.

//global fun
fun test(){
    val p = Pokemon("Pikatchu")
    val p2 = Pokemon("Pikatchu", 10.1)
    val p3 = Pokemon("Pikatchu", 10.1, 2)
}