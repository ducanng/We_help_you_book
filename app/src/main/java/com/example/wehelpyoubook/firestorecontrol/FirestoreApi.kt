package com.example.wehelpyoubook.firestorecontrol

import android.content.Context
import androidx.navigation.NavDestination
import com.example.wehelpyoubook.model.*
import com.example.wehelpyoubook.scrapingdata.db
import com.google.firebase.firestore.ktx.toObjects

class FirestoreApi {
    fun removeReview(){
        db.collection("Reviews").whereEqualTo("useId","qJbnhefoYGQENQE8JAgDCpimoR42").get().addOnSuccessListener {
            doc -> while (doc.documents.isNotEmpty()){
                println("dang xoa")
            db.collection("Reviews").document(doc.documents[0].id).delete()
        }
        }
    }
}