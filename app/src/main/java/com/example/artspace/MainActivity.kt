package com.example.artspace

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceApp()
        }
    }
}

// Data class to represent an artwork
data class Artwork(val title: String, val description: String, val imageUrl: Int)

// Sample list of artworks
val artworkList = listOf(
    Artwork(
        "Full Metal Alchemist",
        "Created by Hiromu Arakawa (2001)",
        R.drawable.fullmetal
    ),
    Artwork(
        "Bleach",
        "Created by Tite Kubo (2001)",
        R.drawable.bleach
    ),
    Artwork(
        "Yu-Gi-Oh!",
        "Created by Kazuki Takahashi (1996)",
        R.drawable.yugioh
    ),
    Artwork(
        "Code Geass",
        "Created by Ichiro Okouchi (2006)",
        R.drawable.code_geass
    )
    // Add more artworks as needed
)

@Composable
fun ArtSpaceApp() {
    val artworkIndex = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1f))

        // Image with border drawn around it
        ImageWithBorder(
            imageResource = artworkList[artworkIndex.intValue].imageUrl,
            modifier = Modifier.size(350.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            //modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    //.fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(1.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = artworkList[artworkIndex.intValue].title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 40.dp)
                    )
                    Text(
                        text = artworkList[artworkIndex.intValue].description,
                        style = MaterialTheme.typography.bodySmall,
                        color = LocalContentColor.current.copy(alpha = 0.8f),
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 40.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                artworkIndex.intValue =
                    (artworkIndex.intValue - 1).coerceIn(0, artworkList.size - 1)
            }) {
                Text("Previous")
            }
            Button(onClick = {
                artworkIndex.intValue =
                    (artworkIndex.intValue + 1).coerceIn(0, artworkList.size - 1)
            }) {
                Text("Next")
            }
        }
    }
}




@Composable
fun ImageWithBorder(
    imageResource: Int,
    modifier: Modifier = Modifier,
    borderStrokeWidth: Dp = 2.dp,
    borderColor: Color = Color.LightGray,
    contentScale: ContentScale = ContentScale.Crop
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            contentScale = contentScale,
            modifier = Modifier.matchParentSize()
        )

        Canvas(modifier = Modifier.matchParentSize()) {
            val strokeWidthPx = borderStrokeWidth.toPx()
            val halfStrokeWidth = strokeWidthPx / 2
            val borderSize = Size(size.width - strokeWidthPx, size.height - strokeWidthPx)

            drawRoundRect(
                color = borderColor,
                topLeft = Offset(halfStrokeWidth, halfStrokeWidth),
                size = borderSize,
                cornerRadius = CornerRadius(8f),
                style = Stroke(width = strokeWidthPx)
            )
        }
    }
}

@Preview
@Composable
fun PreviewArtSpaceApp() {
    ArtSpaceApp()
}

