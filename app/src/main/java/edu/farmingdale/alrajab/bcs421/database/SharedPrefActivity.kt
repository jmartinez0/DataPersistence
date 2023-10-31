package edu.farmingdale.alrajab.bcs421.database

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.farmingdale.alrajab.bcs421.MainActivity
import edu.farmingdale.alrajab.bcs421.R


class SharedPrefActivity : AppCompatActivity() {

    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var saveButton: Button
    lateinit var readButton: Button
    lateinit var updateButton: Button
    lateinit var backButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_pref)

        firstName = findViewById(R.id.firstNameEditText)
        lastName = findViewById(R.id.lastNameEditText)

        val sharedPreferences = getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE)

        saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            val firstName = firstName.text.toString()
            val lastName = lastName.text.toString()
            var editor = sharedPreferences.edit()
            editor.putString("first_name", firstName)
            editor.putString("last_name", lastName)
            editor.apply()
            Toast.makeText(this, "Data saved.", Toast.LENGTH_SHORT).show()
        }

        readButton = findViewById<Button>(R.id.readButton)
        readButton.setOnClickListener {
            val firstName = sharedPreferences.getString("first_name", "")
            val lastName = sharedPreferences.getString("last_name", "")
            val fullName = "$firstName $lastName"
            // Dynamically create a text view for the purposes of reading the shared pref
            val dynamicTextView = TextView(this)
            dynamicTextView.text = fullName
            dynamicTextView.textSize = 20f
            dynamicTextView.setTypeface(null, Typeface.BOLD)
            dynamicTextView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            // Add dynamic text view to our linear layout
            val linearLayout = findViewById<LinearLayout>(R.id.linear_layout)
            linearLayout.addView(dynamicTextView)
            Toast.makeText(this, "Data read.", Toast.LENGTH_SHORT).show()
        }

        updateButton = findViewById<Button>(R.id.updateButton)
        updateButton.setOnClickListener {
            val firstName = firstName.text.toString()
            val lastName = lastName.text.toString()
            val editor = sharedPreferences.edit()
            editor.putString("first_name", firstName)
            editor.putString("last_name", lastName)
            editor.apply()
            Toast.makeText(this, "Data updated.", Toast.LENGTH_SHORT).show()
        }

        backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener { backToMainActivity() }
    }


    /**
     * Go back to main activity.
     */
    private fun backToMainActivity() {
        startActivity( Intent(this, MainActivity::class.java) )
    }

}