package com.example.wehelpyoubook.firestorecontrol

import com.example.wehelpyoubook.scrapingdata.db

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