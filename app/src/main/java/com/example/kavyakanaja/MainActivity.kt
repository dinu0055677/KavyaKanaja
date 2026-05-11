package com.example.kavyakanaja

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import kotlinx.coroutines.delay
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            KavyaKanajaApp()
        }
    }
}

data class Poem(
    val title: String,
    val author: String,
    val content: String,
    val meaning: String,
    val audio: String
)

val poems = listOf(
    Poem("Vachana 1", "Basavanna", "ಕಾಯಕವೇ ಕೈಲಾಸ", "Work is worship. Doing work sincerely is devotion.", "audio1.mp3"),
    Poem("Vachana 2", "Akka Mahadevi", "ಲೋಕದ ಚೇಷ್ಟೆಗೆ ರವಿ ಬೀಜವಾದಂತೆ", "Just as the sun is the cause of the world's activities.", "audio2.mp3"),
    Poem("Vachana 3", "Allama Prabhu", "ಕಾಣಬಾರದ ಹೊಳಹಿನೊಳಗೆ", "Within the light that cannot be seen.", "audio3.mp3"),
    Poem("Vachana 4", "Sarvajna", "ಜ್ಞಾನಿಯಾದವನು ಜಗಕೆ ಬೆಳಕು", "A wise person is a light to the world.", "audio4.mp3"),
    Poem("Vachana 5", "Kanaka Dasa", "ನಾನು ಪರೀಕ್ಷಿಸಿ ನೋಡಿದೆ", "I have examined and seen.", "audio5.mp3"),
    Poem("Vachana 6", "Purandara Dasa", "ಕಲ್ಯಾಣಪುರದಲಿ ನೆಲೆಸಿದ ವಿಠಲ", "Vitthala who resided in Kalyanapura.", "audio6.mp3"),
    Poem("Vachana 7", "Kuvempu", "ಓ ನನ್ನ ಚೇತನ ಆಗು ನೀ ಅನಿಕೇತನ", "O my spirit, become unhoused.", "audio7.mp3"),
    Poem("Vachana 8", "D. R. Bendre", "ಬಾರೋ ಸಾಧನಕೇರಿ ಗೆ", "Come to Sadhanakeri.", "audio1.mp3"),
    Poem("Vachana 9", "Masti Venkatesha Iyengar", "ಸತ್ಯದ ಹಾದಿಯಲಿ", "On the path of truth.", "audio2.mp3"),
    Poem("Vachana 10", "Gopalakrishna Adiga", "ಯಾವ ಕಾಲದ ಶಾಸ್ತ್ರ", "Which era's scriptures?", "audio3.mp3"),
    Poem("Vachana 11", "U. R. Ananthamurthy", "ಸಂಸ್ಕಾರದ ನೆರಳು", "The shadow of culture.", "audio4.mp3"),
    Poem("Vachana 12", "Shivaram Karanth", "ಬೆಟ್ಟದ ಜೀವ", "The life of the mountain.", "audio5.mp3"),
    Poem("Vachana 13", "Girish Karnad", "ತುಘಲಕ್", "Tughlaq.", "audio6.mp3"),
    Poem("Vachana 14", "Chandrashekhara Kambara", "ಜೋಕುಮಾರಸ್ವಾಮಿ", "Jokumaraswamy.", "audio7.mp3"),
    Poem("Vachana 15", "Vaidehi", "ಅಸ್ಪೃಶ್ಯರು", "The Untouchables.", "audio1.mp3")
)

data class Poet(val name: String, val bio: String, val works: String)

val poetsList = listOf(
    Poet("Basavanna", "12th-century philosopher and social reformer.", "Famous for Vachanas promoting equality."),
    Poet("Akka Mahadevi", "Great Kannada poet and devotee of Lord Shiva.", "Known for spiritual and philosophical vachanas."),
    Poet("Allama Prabhu", "Mystic saint and poet of the 12th century.", "Deep philosophical Vachanas."),
    Poet("Sarvajna", "Kannada poet known for his Tripadis.", "Writings on ethics and society."),
    Poet("Kanaka Dasa", "Poet, philosopher, and musician.", "Famous Haridasa compositions."),
    Poet("Purandara Dasa", "Founding father of Carnatic music.", "Vast collection of devotional songs."),
    Poet("Kuvempu", "National poet of India from Karnataka.", "Famous for Ramayana Darshanam."),
    Poet("D. R. Bendre", "Celebrated Kannada poet and Jnanpith winner.", "Lyrical poetry like Nakutanti."),
    Poet("Masti Venkatesha Iyengar", "Father of Kannada short stories.", "Famous works like Chikkaveera Rajendra."),
    Poet("Gopalakrishna Adiga", "Pioneer of the Navyya movement in Kannada.", "Reflective poetry like Chande Maddale."),
    Poet("U. R. Ananthamurthy", "Renowned writer and critic.", "Famous novel Samskara."),
    Poet("Shivaram Karanth", "Polymath and Jnanpith awardee.", "Epic novels like Mookajjiya Kanasugalu."),
    Poet("Girish Karnad", "Legendary playwright and actor.", "Plays like Tughlaq and Hayavadana."),
    Poet("Chandrashekhara Kambara", "Poet, playwright and folklorist.", "Rich folklore-inspired works."),
    Poet("Vaidehi", "Acclaimed writer known for her short stories.", "Focus on women's lives and social issues.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KavyaKanajaApp() {
    val navController = rememberNavController()
    val bgGradient = Brush.verticalGradient(listOf(Color(0xFF1A237E), Color(0xFF6A1B9A)))
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val infiniteTransition = rememberInfiniteTransition(label = "logoAnimation")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    val rotation by infiniteTransition.animateFloat(
        initialValue = -3f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(2400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotation"
    )

    Box(modifier = Modifier.fillMaxSize().background(bgGradient)) {
        Column(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // LEFT SIDE: Back Button / Logo
                Box(modifier = Modifier.size(40.dp), contentAlignment = Alignment.Center) {
                    if (currentRoute == "poets") {
                        IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.size(26.dp)) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "App Logo",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .size(40.dp)
                                .graphicsLayer(
                                    scaleX = scale,
                                    scaleY = scale,
                                    rotationZ = rotation
                                )
                        )
                    }
                }

                // CENTER: App Title
                Text(
                    text = if (currentRoute == "poets") "Poet's Corner" else "Kavya Kanaja",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // RIGHT SIDE: Profile/Nav / Logo
                Box(modifier = Modifier.size(40.dp), contentAlignment = Alignment.Center) {
                    if (currentRoute == "home") {
                        IconButton(onClick = { navController.navigate("poets") }, modifier = Modifier.size(26.dp)) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Poets",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    } else if (currentRoute == "poets") {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "App Logo",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .size(40.dp)
                                .graphicsLayer(
                                    scaleX = scale,
                                    scaleY = scale,
                                    rotationZ = rotation
                                )
                        )
                    }
                }
            }

            // Main Content Area
            Box(modifier = Modifier.weight(1f)) {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { PoetryApp() }
                    composable("poets") { PoetsScreen() }
                }
            }
        }
    }
}

@Composable
fun PoetryApp() {
    var currentIndex by remember { mutableIntStateOf(0) }
    val poem = poems[currentIndex]
    val context = LocalContext.current
    var showMeaning by remember { mutableStateOf(false) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) }

    // Real-time slider position and timestamps
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var currentTime by remember { mutableStateOf("00:00") }
    var totalTime by remember { mutableStateOf("00:00") }

    fun formatTime(ms: Int): String {
        val totalSeconds = (ms / 1000).coerceAtLeast(0)
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return "%02d:%02d".format(minutes, seconds)
    }

    fun playAudio(audioName: String) {
        showMeaning = true
        mediaPlayer?.release()
        val resId = getAudioRes(context, audioName)
        if (resId != 0) {
            mediaPlayer = MediaPlayer.create(context, resId).apply {
                totalTime = formatTime(duration)
                start()
                isPlaying = true
                setOnCompletionListener { 
                    isPlaying = false 
                    sliderPosition = 1f
                    currentTime = totalTime
                }
            }
        }
    }

    // Update slider while playing
    LaunchedEffect(isPlaying, currentIndex) {
        if (isPlaying) {
            while (isPlaying) {
                mediaPlayer?.let { mp ->
                    if (mp.isPlaying && mp.duration > 0) {
                        sliderPosition = mp.currentPosition.toFloat() / mp.duration.toFloat()
                        currentTime = formatTime(mp.currentPosition)
                        totalTime = formatTime(mp.duration)
                    }
                }
                delay(500)
            }
        } else if (mediaPlayer == null) {
            sliderPosition = 0f
            currentTime = "00:00"
            totalTime = "00:00"
        }
    }

    DisposableEffect(currentIndex) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
            isPlaying = false
            sliderPosition = 0f
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 10.dp), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showMeaning = !showMeaning },
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    // Header: TITLE and AUTHOR
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = poem.title.uppercase(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        )
                        Text(
                            text = poem.author.uppercase(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // Poem Content: Centered and Enlarged
                    Text(
                        text = poem.content,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 120.dp)
                    )

                    if (showMeaning) {
                        Text(
                            text = "ಭಾವಾರ್ಥ: ${poem.meaning}",
                            color = Color(0xFF6A1B9A),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // Music Player Progress Section (Seekable)
                    Slider(
                        value = sliderPosition,
                        onValueChange = { 
                            sliderPosition = it
                            mediaPlayer?.let { mp ->
                                val seekTo = (it * mp.duration).toInt()
                                mp.seekTo(seekTo)
                                currentTime = formatTime(seekTo)
                            }
                        },
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFF1A237E),
                            activeTrackColor = Color(0xFF1A237E),
                            inactiveTrackColor = Color(0xFFE0E0E0)
                        )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(currentTime, fontSize = 14.sp, color = Color.Gray)
                        Text(totalTime, fontSize = 14.sp, color = Color.Gray)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Playback Controls
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Shuffle,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )

                        IconButton(onClick = {
                            currentIndex = if (currentIndex - 1 < 0) poems.size - 1 else currentIndex - 1
                        }) {
                            Icon(
                                imageVector = Icons.Default.SkipPrevious,
                                contentDescription = null,
                                modifier = Modifier.size(36.dp),
                                tint = Color.Black
                            )
                        }

                        // Main Circular Play/Pause Button
                        Surface(
                            onClick = {
                                if (isPlaying) {
                                    mediaPlayer?.pause()
                                    isPlaying = false
                                } else {
                                    showMeaning = true
                                    if (mediaPlayer == null) playAudio(poem.audio) else {
                                        mediaPlayer?.start()
                                        isPlaying = true
                                    }
                                }
                            },
                            shape = RoundedCornerShape(50),
                            color = Color.Black,
                            modifier = Modifier.size(56.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }

                        IconButton(onClick = {
                            currentIndex = (currentIndex + 1) % poems.size
                        }) {
                            Icon(
                                imageVector = Icons.Default.SkipNext,
                                contentDescription = null,
                                modifier = Modifier.size(36.dp),
                                tint = Color.Black
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.Repeat,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PoetsScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(poetsList) { poet ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Text(
                                text = poet.name,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1A237E)
                            )
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 16.dp),
                                color = Color(0xFFEEEEEE)
                            )
                            Text(
                                text = "Bio: ${poet.bio}",
                                fontSize = 16.sp,
                                color = Color.DarkGray,
                                lineHeight = 22.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Works: ${poet.works}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getAudioRes(context: Context, name: String): Int {
    return context.resources.getIdentifier(name.substringBefore("."), "raw", context.packageName)
}

@Preview(showBackground = true)
@Composable
fun KavyaKanajaAppPreview() {
    KavyaKanajaApp()
}

@Preview(showBackground = true)
@Composable
fun PoetsScreenPreview() {
    val bgGradient = Brush.verticalGradient(listOf(Color(0xFF1A237E), Color(0xFF6A1B9A)))
    Box(modifier = Modifier.fillMaxSize().background(bgGradient)) {
        PoetsScreen()
    }
}
