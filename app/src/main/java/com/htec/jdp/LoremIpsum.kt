package com.htec.jdp

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan

class LoremIpsum {
    companion object {
        @JvmStatic
        fun getText() = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent dignissim urna tellus, et tristique odio blandit nec. Nullam commodo, tortor ut vestibulum convallis, massa quam accumsan lectus, in cursus felis mauris eget arcu. Praesent id quam tincidunt, pretium lectus et, semper sapien. Quisque ac consequat nisl. Sed scelerisque diam ut mauris tristique, eget ultricies nulla consectetur. Aliquam vehicula iaculis urna vitae varius. Nulla quis odio lacinia, sollicitudin velit luctus, malesuada leo. Donec ut nisl nec neque tristique facilisis cursus eu sem. Etiam consequat rutrum ante, eget eleifend nunc luctus eget. Sed dictum vulputate elit a pellentesque. Nunc bibendum in sapien ac scelerisque. Phasellus in orci eu mauris iaculis iaculis.\n" +
                "\n" +
                "Etiam dignissim orci eu maximus sagittis. Donec in porttitor massa, nec viverra tellus. Phasellus sit amet maximus nisl. Curabitur vestibulum quam justo, non faucibus turpis aliquet sed. Curabitur eros mi, faucibus in sem a, tempor dignissim tellus. Nulla et augue id elit blandit fermentum quis a felis. Aliquam erat volutpat. Morbi malesuada cursus leo, ac imperdiet augue sollicitudin in. Maecenas pretium condimentum neque, eget congue purus condimentum commodo.\n" +
                "\n" +
                "Donec imperdiet nisl et pharetra malesuada. Curabitur eu laoreet libero. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras porttitor, erat eu consequat pretium, tellus leo ullamcorper diam, in pellentesque nulla enim vel nibh. Vestibulum dapibus, sapien ac imperdiet aliquam, nunc tortor tempus elit, nec eleifend urna tortor in nisl. Curabitur felis diam, varius sed hendrerit quis, consectetur et ante. Sed vel maximus nisi.\n" +
                "\n" +
                "In et diam quis magna tempor rutrum. Vestibulum sollicitudin leo orci, porttitor malesuada enim malesuada sit amet. Aliquam dapibus malesuada massa et tincidunt. Praesent hendrerit justo auctor neque aliquam pellentesque. Sed eu arcu maximus, pulvinar mi nec, iaculis mi. Praesent sit amet elit quam. Quisque aliquam ante vulputate gravida commodo. Fusce aliquam nulla a ligula molestie ornare. Aliquam non dictum mauris, sed vestibulum ex. Vivamus congue elit urna, et malesuada neque interdum a. Ut lobortis ante magna, non pharetra eros volutpat sit amet. Cras nec eros cursus, ultricies neque ut, lobortis neque. Vestibulum sit amet mi id urna luctus gravida a eu tortor. Nam tempus viverra enim, sit amet cursus leo gravida vitae.\n" +
                "\n" +
                "Proin vestibulum lacus sed velit dignissim, sit amet pharetra lectus placerat. Mauris rutrum lectus sit amet libero ornare, consectetur tempor metus mollis. Donec fermentum justo id lobortis condimentum. Nam tincidunt mattis leo nec sagittis. Vestibulum vehicula non lorem commodo tincidunt. Donec pharetra purus eu ultricies dignissim. Vestibulum sit amet augue orci. Fusce eleifend porta velit quis facilisis. Praesent porttitor tortor vel dolor aliquet consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ac quam mattis, tincidunt nisl nec, gravida diam. Curabitur tempor laoreet velit vitae fermentum. Nunc rhoncus sollicitudin ullamcorper. Etiam libero ligula, rhoncus non ullamcorper ut, auctor eu diam. Pellentesque suscipit nibh ac eros varius, quis vulputate lectus hendrerit. Nunc ex sem, commodo aliquam convallis sodales, sollicitudin et enim."


        private var selectableColor = Color.YELLOW
        fun setSelectableColor(newColor : Int)  { selectableColor = newColor }

        fun getSelected(query: String, fullText: CharSequence): CharSequence {
            val spannableString = SpannableString(fullText)
            var spanStart = 0
            var spanEnd = 0
            while(true) {
                spanStart = spannableString.indexOf(query, spanEnd)

                if(spanStart == -1) { break }

                spanEnd = spanStart+query.length
                if(spanEnd > spannableString.length) spanEnd= spannableString.length

                spannableString.setSpan(BackgroundColorSpan(selectableColor), spanStart, spanEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            return spannableString
        }
    }
}