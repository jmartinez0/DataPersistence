package edu.farmingdale.alrajab.bcs421.database

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import edu.farmingdale.alrajab.bcs421.MainActivity
import edu.farmingdale.alrajab.bcs421.databinding.ActivityDatabaseBinding
import kotlin.random.Random

class DatabaseActivity : AppCompatActivity() {

    private lateinit var binding:  ActivityDatabaseBinding

    private lateinit var dbHelper:NameRepository

    // ToDO: Database link to be completed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper= NameRepository.getInstance(this)

        binding.readData.setOnClickListener { readData() }
        binding.writeData.setOnClickListener { writeData() }
        binding.updateButton.setOnClickListener { updateData() } // Add this line
        binding.backButton.setOnClickListener { backToMainActivity() }
    }

    private fun writeData() {
        /*
        dbHelper.addUser(User("Course "+Random.nextInt(6000),
            "CSC "+Random.nextInt(6000)))
         */
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()

        if (firstName.isNotBlank() && lastName.isNotBlank()) {
            dbHelper.addUser(User(firstName, lastName))
            Toast.makeText(this, "Data saved.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "All fields must be filled.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun readData() {
        dbHelper.getAll().forEach { Log.d("Data",it.firstName+" , "+ it.lastName) }
    }

    private fun updateData() {
        val oldFirstName = binding.firstNameEditText.text.toString()
        val oldLastName = binding.lastNameEditText.text.toString()
        val newFirstName = binding.newFirst.text.toString()
        val newLastName = binding.newLast.text.toString()

        // All fields must be filled to do an update
        if (oldFirstName.isNotBlank() && oldLastName.isNotBlank() &&
            newFirstName.isNotBlank() && newLastName.isNotBlank()
        ) {
            val user = dbHelper.findUserByName(oldFirstName, oldLastName)

            if (user != null) {
                user.firstName = newFirstName
                user.lastName = newLastName
                dbHelper.updateUser(user)
                Toast.makeText(this, "Data updated.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "User not found. Invalid first/last name.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "All fields must be filled.", Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * Go back to main activity.
     */
    private fun backToMainActivity() {
        startActivity( Intent(this, MainActivity::class.java) )
    }
}