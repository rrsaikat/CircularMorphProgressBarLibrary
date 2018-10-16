# CircularMorphProgressBarLibrary
![https://github.com/rrsaikat/CircularMorphProgressBar](https://img.shields.io/badge/platform-Android-green.svg?style=flat-square)
![https://github.com/rrsaikat/CircularMorphProgressBar](https://img.shields.io/badge/API-16+-orange.svg?style=flat-square)
![https://www.apache.org/licenses/LICENSE-2.0](https://img.shields.io/badge/licence-Apache%20v2.0-blue.svg?style=flat-square)

A simple library for reveal a circular progressbar with circular morph layout.

<p align="start">
  <img src="https://github.com/rrsaikat/CircularMorphProgressBarLibrary/blob/master/start.gif" height="390" width="250"/>
</p>



# How to
To get this Git project into your build:

Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

      allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
      }
      
      
Step 2. Add the dependency

      dependencies {
              implementation 'com.github.rrsaikat:CircularMorphProgressBarLibrary:0.1.0' 

      }


Srep 3. Sync & build.



### Usage ([sample](https://github.com/rrsaikat/SampleOfCircularMorphProgressBar))



XML
-----

    <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="10dp"
        tools:context=".MainActivity">


      <com.rezwan.pstu.cse12.view.CircularMorphLayout
          android:id="@+id/cml_proceed_layout"
          android:layout_width="match_parent"
          android:layout_height="48dp"
          android:layout_centerInParent="true">

          <TextView
              android:id="@+id/tv_proceed"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:background="@drawable/background_btn"
              android:clickable="true"
              android:focusable="true"
              android:gravity="center"
              android:textStyle="bold"
              android:typeface="monospace"
              android:text="Start"
              android:textAllCaps="true"
              android:textColor="@android:color/white"
              />
      </com.rezwan.pstu.cse12.view.CircularMorphLayout>

      <ProgressBar
          android:id="@+id/progressBar"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_centerInParent="true"
          android:visibility="gone"
          style="@style/Widget.AppCompat.ProgressBar"
          />

      </RelativeLayout>
      
      
      
JAVA
-----

        cmLayout = (CircularMorphLayout)findViewById(R.id.cml_proceed_layout);
        pb =(ProgressBar)findViewById(R.id.progressBar);
        btStop =(Button)findViewById(R.id.btnStop);
        mStartButtonTxt = (TextView)findViewById(R.id.tv_proceed);
        mStartButtonTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmLayout.setDuration(800); //duration between 100 to 1000
                cmLayout.revealFrom(mStartButtonTxt.getWidth() / 2f,
                        mStartButtonTxt.getHeight() / 2f,
                        mStartButtonTxt.getWidth() / 2f,
                        mStartButtonTxt.getHeight() / 2f).setListener(
                        () -> {
                            mStartButtonTxt.setVisibility(View.GONE);
                            pb.setVisibility(View.VISIBLE);
                        }).start();

            }
        });
  
 
NOTE
-----

For using lembda expression () -> java language must be upto 8<=



## Reverse Function

To stop interpolation or reverse the CircularMorphLayout use this function called <b>reverse()</b>

<p align="start">
  <img src="https://github.com/rrsaikat/CircularMorphProgressBarLibrary/blob/master/start.gif" height="390" width="250"/>
</p>

## Example 

       btStop =(Button)findViewById(R.id.btnStop);
       btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStartButtonTxt.setVisibility(View.VISIBLE);
                pb.setVisibility(View.INVISIBLE);
                cmLayout.reverse();
            }
        });
        

LICENCE
-----

 Copyright 2018 Md. Rezwan Rabbi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
