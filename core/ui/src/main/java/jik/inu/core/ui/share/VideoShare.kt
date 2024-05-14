package jik.inu.core.ui.share

import android.content.Intent

val videoShareIntent: (thumbnailUrl: String, videoUrl: String, description: String) -> Intent =
    { thumbnailUrl, videoUrl, description ->

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT, "" +
                        "INUgram 에서 공유한 영상이에요.\n" +
                        "$description\n\n$videoUrl"
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        shareIntent
    }
