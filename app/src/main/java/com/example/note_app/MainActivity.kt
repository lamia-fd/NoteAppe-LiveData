package com.example.note_app
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //  val userNote = ArrayList<String>()
    lateinit var ev1: TextView

    lateinit var button: Button
    private lateinit var rvAdapter:recycler

    lateinit var myRv:RecyclerView
    private val viewModel by lazy { ViewModelProvider(this).get(viewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ev1 = findViewById(R.id.tv1)
        val layout = findViewById<ConstraintLayout>(R.id.layout)
      //  viewModel = ViewModelProvider(this).get(viewModel::class.java)
        viewModel.getNotes().observe(this, {
                notes -> rvAdapter.update(notes)
        })





        // noteList= arrayListOf()
     //   NoteDataBase.getInstance()
         myRv = findViewById(R.id.rvMain)
        layout.setBackgroundResource(R.drawable.background)
        button = findViewById(R.id.button)


        rvAdapter= recycler(this)
        myRv.adapter = rvAdapter
        myRv.layoutManager = LinearLayoutManager(this)

        button.setOnClickListener {
            if(ev1.text.isNotBlank()){
                var n=NotesData(0,ev1.text.toString())
                viewModel.addNote(ev1.text.toString())
            }else{
                Toast.makeText(applicationContext, "write a note", Toast.LENGTH_LONG).show()

            }

        }

    }



    fun openDialog(id:Int){

        val dialogBuilder = AlertDialog.Builder(this)
        val updatedNote = EditText(this)
        dialogBuilder.setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {
                    _, _ ->  viewModel.editNote(id, updatedNote.text.toString())


            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(updatedNote)
        alert.show()

    }

    fun deleteNotes(id: Int){
        viewModel.deleteNote(id)




    }




}