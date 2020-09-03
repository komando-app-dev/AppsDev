package tomerbu.edu.lec21kotlin

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import java.lang.Exception

import kotlin.math.PI
import kotlin.math.cos


//Basic activity:
class MainActivity : AppCompatActivity() {

    //compile time const


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


    }

    //מה זה Unit
    //למה צריך Unit הרי יש Void
    //Unit Test -> תוך כמה זמן זה סיים?
    fun myFunc(): Nothing {
        //פירוש המילה Void זה לא מחזיר כלום.
        //אבל בקוטלין יש טיפוס שקוראין לא "כלום"

        //מחלקה שיורשת מException
        class Catchup : Exception()

        //פונקציה שזורקת קטשופ ולא מחזירה כלום.
        throw Catchup()

    }


    fun f() {
        var woot: Any = "Hello"
        woot = 4
    }


    //הטיפוס הכי מעניין בקוטלין:
    fun nullableTypes() {
        var name: String? = "Moshe" //String
        //name can't be null. //NotNull, Nullable (annotations)
        //NullPointerException - NPE

        //בגלל שעלול להיות null קוטלין מכריחה אותנו לבדוק!

        // name.toUpperCase()

        var y: Int? = 10
        //אפשר לעשות חשבון עם y?
        //y+=1

        if (y != null) {
            println(y + 1)
        }
    }

    //double bang operator.
    fun moreAboutNullables() {
        val toInt = "3".toIntOrNull()

        //אם אנחנו יודעים שבוודאות לא יהיה null
        //מותר לנו להתעלם מהסכנה
        //double bang operator.
        print(toInt!! + 1)
    }

    //the elvis operator:
    fun moreAboutNullables3() {
        //שלום, אורח.
        var user: String? = null //"Tomer"

        //Null Coalescing operator
        println("Hello, ${user ?: "Guest"}")


        if (user != null)
            println("Hello, $user")
        else {
            println("Hello, guest")
        }
    }

    fun moreAboutNulls4() {
        val name: String? = "ototototTomer"

        //print how many distinct characters?

        //safe calls:
        //if the values are not null -> do.
        println(name?.chars()?.distinct()?.count())
    }

    fun arraysAndLists() {
        val arr = arrayOf(10, 22, 33)

        for (i in 0..2) {
            print(arr[i])
        }

        //immutable list:
        val lst1 = listOf(1, 23)
        //no add method

        var mList = mutableListOf<String>()
        //has the add method.
    }

    //function that takes a function as a parameter!
    fun getMoviesAsync(callback: (String) -> Unit) {
        //do work...

        //call the callback
        callback("success")
    }


    fun updateUI(movies: String) {
        println(movies)
    }

    fun demo() {
        //asyncJob נרצה לקרוא לפעולה
        //פעולה שדורשת פונקציה כפרמטר.
        getMoviesAsync(
                ::updateUI
        )


        //method ref.

        //class ref
        //MainActivity::class.java
    }


    //meta programming:
    //ref to methods, classes
    fun main() {
        //method reference:
        var eatRef = ::eat


        //good for callbacks:

        //functions are first class citizens
        eatRef()


    }

    fun eat() {

    }

    //default parameter values:
    fun sayHello(numTimes: Int = 1) {
        repeat(numTimes) {
            println("Hello")
        }

        //numTimes = 6
        //numTimes is a val
        var numTimes = numTimes // copy
        numTimes += 1
        println(numTimes)
    }

    fun sayHello2(name: String) {
        //"String" + "String"
        //Template Strings
        println("Hello, $name") //Hello, TOMER
        println("Hello, ${name.toUpperCase()}") //Hello, TOMER


        val tip = 15 // pct
        val bill = 100

        //println
        //total is ?
        println("Total is: ${tip * bill + bill} :-)")
    }

    fun math() {
        //pi, sin, cos,

        println(PI)

        //Math from kotlin
        cos(3.15)


        //3.1419.....
        //how to print pi in 2 digits after the dot?
        println("%3.2f".format(PI))//3.14
        println("%.1f".format(PI))//3.14
    }

    fun typeConversion() {
        var x = 10 //Int (Ctrl + shift + p)

        var y = 10.0 //Double

        //x = y; //compile time error:

        x = y.toInt()

        val input = "10.1"

        println(input.toDouble()) //crash if the input is invalid

        println(input.toDoubleOrNull()) //no crash -> will get null.


        val g = "good"
        val g2 = "go" + "od"

        //בדיקת שוויון לפי ערך:
        println(g == g2) //true same by value

        //בדיקת שוויון לפי מצביע:
        println(g === g2) // true if both point to same memory address.


        println("ravi" < "davi")//ordinal comparison
    }

    fun statements() {

        //same as java:
        if (3 == 4) {
            println("3 == 4")
        }

        //if can also return a value:
        val x = 1


        //משפט תנאי יכול להחזיר ערך
        //אם x זוגי - Even
        //אם x אי-זוגי - Odd
        val y = if (x % 2 == 0) {
            "Even" //הערך כשהתנאי מתקיים
        } else {
            "Odd"//הערך כשהתנאי לא מתקיים
        }

        println(y)

        //איך עושים משפט תנאי מקוצר - אין משפט תנאי מקוצר - הif הרגיל עושה את העבודה הזאת

        //תרגיל קצר: בלי מסולסלים בבקשה
        val a = 10
        val b = 15 //Random.

        //אם a גדול מ-b הדפיסו גדול אחרת הדפיסו "לא גדול"
        println(if (a < b) "smaller" else "larger")


        //while, do while,
        //todo: discuss it.
        repeat(3) {
            println("Hi, No i") //
        }


        //from 1 to 9 increment by 1
        for (i in 1..9) { //inclusive loop
            //println("*$i")
            println("*")
            //println()
        }

        //step
        for (i in 1..9 step 3) {
            //1, 4, 7 (step is 3)
            print(i)
        }

        //down to:

        //from 10(inclusive) to 1(inclusive)
        for (i in 10 downTo 1) {

        }

        //from 10(inclusive) to 1(exclusive)
        for (i in 10 until 1) {

        }


        //for(int i  = 0 ; i < 10 ; i++)


        val grade = 100


        //switch(grade){}
        when (grade) {
            100 -> print("Wow!")
            99, 98 -> print("Meh")
            //95 to 97?
            in 95..97 -> print("ok...")

            else -> print("slap your kid")
        }


        val eval = when {
            grade < 55 -> "F"
            else -> "Passed"
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}