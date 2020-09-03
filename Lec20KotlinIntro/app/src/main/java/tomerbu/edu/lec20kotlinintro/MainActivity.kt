package tomerbu.edu.lec20kotlinintro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


//no implements / extends here.
// use : and ,
class MainActivity : AppCompatActivity() {


    //? Bundle is nullable -> Bundle may be null. Optional.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Java -> Kotlin using paste

        sayHello("Avi")
    }

    fun haveFun() {
        var x = 10 // x is inferred to be an Int ->
        x += 1
        //Kotlin is type safe. x is an Int.

        val y = 20 // final int y = 20;
        //y += 1


        //במה תבחרו? val עדיף - יותר יעיל.
        //Immutable by default


        //type annotation:
        var z: Int = 10
    }

    fun sayHello(name: String) {

        //אולי זאת פעולה של Activity

        //פונקציה גלובלית: פונקציה שלא בתוך מחלקה.
        // נעדיף לא ליצור פונקציות גלובליות.
        println("Hello, " + name)

        //System.out.println("")
    }


    //no void
    //x():return-type

//    fun x():Int{
//
//    }
}
