package tomerbu.edu.lec21kotlin;

/**
 * Created by tomerbuzaglo on 03/09/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
public class AsyncDemo {

    //כתבו בJAVA פעולה שמקבלת callback של מחרוזת
    interface Callback {
        void done(String result);
    }

    //doInBackground
    public void getMoviesAsync(Callback callback) {
        //while()..input stream... try catch
        //
        //if all is good -> callback()
        callback.done("Success");


        //ArrayList<Void> lst;
    }

    //onPostExecute
    //update the ui once all is ready:
    public void updateUI(String movies) {
        System.out.println(movies);
    }

    void demo() {
        getMoviesAsync(
                this::updateUI
        );
    }
}
