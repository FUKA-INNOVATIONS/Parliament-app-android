package com.fuka.suomeneduskunta.utils.adapters

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fuka.suomeneduskunta.R
import com.fuka.suomeneduskunta.data.database.models.ParliamentMember
import okhttp3.internal.http.HttpHeaders
import java.sql.Connection

// Set party logo for party list items
@BindingAdapter("partyLogo")
fun ImageView.setPartyLogo(item: ParliamentMember) {
    setImageResource(when (item.party) {
        "ps" -> R.drawable.ps_logo
        "sd" -> R.drawable.sdp_logo
        "vihr" -> R.drawable.vihreat_logo
        "kok" -> R.drawable.kokoomus_logo
        "r" -> R.drawable.ruotsalaiset_logo
        "kesk" -> R.drawable.keskusta_logo
        "kd" -> R.drawable.kd_logo
        "vas" -> R.drawable.vasemmisto_logo
        "liik" -> R.drawable.liikenyt_logo
        else -> R.drawable.ic_no_image
    })
}


// Download, chache and bind Parliament member image
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    val baseUrl = "https://avoindata.eduskunta.fi/"
    try {
        imgUrl.let {
            val completeImgUrl = baseUrl + it?.toUri()?.buildUpon()?.build()
            Glide.with(imgView.context)
                .load(completeImgUrl)
                .apply(RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_no_image))
                .into(imgView)
        }
    } catch (e: Error) {
        Log.i("FINFIN", "Glide error: $e")
    }
}



// Set Party full name for party list items
@BindingAdapter("partyName")
fun TextView.setPartyName(item: ParliamentMember) {
    text = (when (item.party) {
        "ps" -> "Perussuomalaiset"
        "sd" -> "Sosiaalidemokraatit"
        "vihr" -> "Vihreät"
        "kok" -> "Kokoomus"
        "r" -> "Ruotsalainen"
        "kesk" -> "Keskusta"
        "kd" -> "Kristillisdemokraatit"
        "vas" -> "Vasemmistoliitto"
        "liik" -> "Liike Nyt"
        else -> "Tuntematon puolue"
    })
}


// Set member status: minister or not -> party members list
@BindingAdapter("ministerStatus")
fun TextView.setMemberStatus(item: ParliamentMember) {

    if ( item.hetekaId == 1297 ) text = "Pääministeri" else {
        text = (when (item.minister) {
            true -> "Ministeri"
            false -> "Kansanedustaja"
        })
    }
}




// Temporary solution for fixing Null issue on member detail view
@BindingAdapter("ministerStatusOnMemberDetail")
fun TextView.setMinisterStatusOnMemberDetail(status: String) {

        text = (when (status) {
            "true" -> "Ministeri"
            "false" -> "Kansanedustaja"
            else -> "Puoluejäsen"
        })
}



// Set member full name
@BindingAdapter("memberFullName")
fun TextView.setMemberFullName(member: ParliamentMember) {
    text = "${member.firstname} ${member.lastname}"
}