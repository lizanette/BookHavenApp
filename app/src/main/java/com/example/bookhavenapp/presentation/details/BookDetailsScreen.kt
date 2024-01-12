package com.example.bookhavenapp.presentation.details

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookhavenapp.R
import com.example.bookhavenapp.domain.model.AccessInfo
import com.example.bookhavenapp.domain.model.Epub
import com.example.bookhavenapp.domain.model.ImageLinks
import com.example.bookhavenapp.domain.model.Item
import com.example.bookhavenapp.domain.model.ListPrice
import com.example.bookhavenapp.domain.model.PanelizationSummary
import com.example.bookhavenapp.domain.model.Pdf
import com.example.bookhavenapp.domain.model.ReadingModes
import com.example.bookhavenapp.domain.model.RetailPriceX
import com.example.bookhavenapp.domain.model.SaleInfo
import com.example.bookhavenapp.domain.model.SearchInfo
import com.example.bookhavenapp.domain.model.VolumeInfo
import com.example.bookhavenapp.presentation.common.CategoryTag
import com.example.bookhavenapp.presentation.common.Dimensions.BigIconSize
import com.example.bookhavenapp.presentation.common.Dimensions.BookItemHeight
import com.example.bookhavenapp.presentation.common.Dimensions.BookItemWidth
import com.example.bookhavenapp.presentation.common.Dimensions.IconSize
import com.example.bookhavenapp.presentation.common.Dimensions.MediumPadding1
import com.example.bookhavenapp.presentation.common.Dimensions.MediumPadding3
import com.example.bookhavenapp.presentation.details.components.BookDetailsTopBar
import com.example.bookhavenapp.ui.theme.BookHavenAppTheme
import com.example.bookhavenapp.ui.theme.Purple
import com.example.bookhavenapp.ui.theme.Yellow

@Composable
fun BookDetailsScreen(
    book: Item,
    event: (BookDetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    var isBookSaved by remember { mutableStateOf(false) }

    DisposableEffect(book) {
        event(BookDetailsEvent.CheckIfBookIsSaved(book) { saved ->
            isBookSaved = saved
        })

        onDispose {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        BookDetailsTopBar(
            onPreviewClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(book.volumeInfo.previewLink)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onGetInfoClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(book.volumeInfo.infoLink)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, book.volumeInfo.infoLink)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                bottom = MediumPadding1
            )
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .height(BookItemHeight)
                            .width(BookItemWidth)
                            .clip(RectangleShape),
                        contentScale = ContentScale.Crop,
                        model = ImageRequest
                            .Builder(context)
                            .data(book.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://"))
                            .build(),
                        error = painterResource(id = R.drawable.placeholder),
                        contentDescription = "Book Cover"
                    )

                    Spacer(modifier = Modifier.height(MediumPadding3))

                    Text(
                        text = book.volumeInfo.title,
                        textAlign = TextAlign.Center,
                        style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 24.sp,
                        color = colorResource(id = R.color.text_title)
                    )
                    Spacer(modifier = Modifier.height(MediumPadding3))

                    Text(
                        text = book.volumeInfo.authors?.first() ?: "Unknown Author",
                        style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.text_title)
                    )

                    Spacer(modifier = Modifier.height(MediumPadding1))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = "Star Icon",
                                        tint = Yellow,
                                        modifier = Modifier
                                            .padding(bottom = 1.dp, end = 2.dp)
                                            .size(IconSize),
                                    )
                                    Text(
                                        text = (book.volumeInfo.averageRating ?: 0).toString(),
                                        fontSize = 20.sp,
                                        style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                        color = colorResource(id = R.color.text_title),
                                    )
                                }
                                Text(
                                    text = "${book.volumeInfo.ratingsCount ?: 0} reviews",
                                    fontSize = 14.sp,
                                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = colorResource(id = R.color.text_medium),
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = (book.volumeInfo.pageCount ?: "Unknown").toString(),
                                    fontSize = 20.sp,
                                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    color = colorResource(id = R.color.text_title),
                                )
                                Text(
                                    text = "Pages",
                                    fontSize = 14.sp,
                                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = colorResource(id = R.color.text_medium),
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = formatPrice(book.saleInfo.listPrice?.amount),
                                    fontSize = 20.sp,
                                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    color = colorResource(id = R.color.text_title),
                                )
                                Text(
                                    text = (book.saleInfo.listPrice?.currencyCode ?: "Price").toString(),
                                    fontSize = 14.sp,
                                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = colorResource(id = R.color.text_medium),
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(MediumPadding1))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 10.dp,
                            pressedElevation = 15.dp
                        ),
                        onClick = {
                            event(BookDetailsEvent.UpsertDeleteBook(book))
                            isBookSaved = !isBookSaved
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Purple,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(BigIconSize)
                                .padding(end = 5.dp),
                            imageVector = if (isBookSaved) Icons.Default.Delete else Icons.Default.Favorite,
                            contentDescription = if (isBookSaved) "Delete" else "Favorite",
                        )
                        Text(
                            text = if (isBookSaved) "Delete from Bookmark list" else "Save this book",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }

                Column(Modifier.padding(top = MediumPadding1)) {
                    if (!book.volumeInfo.categories.isNullOrEmpty()) {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(book.volumeInfo.categories) { item ->
                                CategoryTag(item)
                            }
                        }
                    }

                    if(!book.volumeInfo.description.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height(MediumPadding1))

                        Text(
                            text = "Description",
                            fontSize = 16.sp,
                            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = colorResource(id = R.color.text_title),
                        )

                        Spacer(modifier = Modifier.height(MediumPadding3))

                        Text(
                            text = book.volumeInfo.description,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify,
                            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = colorResource(id = R.color.text_medium),
                        )
                    }
                }
            }
        }
    }
}

fun formatPrice(price: Double?): String = if (price != null) "$${price}" else "Unknown"

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsScreenPreview() {
    BookHavenAppTheme {
        BookDetailsScreen(
            book = Item(
                kind = "books#volume",
                id = "opa1DgAAQBAJ",
                etag = "hZQfhpQKRho",
                selfLink = "https://www.googleapis.com/books/v1/volumes/opa1DgAAQBAJ",
                volumeInfo = VolumeInfo(
                    title = "Nada",
                    authors = listOf("Janne Teller"),
                    publisher = "Seix Barral México",
                    publishedDate = "2017-05-15",
                    description = "Pierre Antón deja el colegio el día que descubre que la vida no tiene sentido. Se sube a un ciruelo y declama a gritos las razones por las que nada importa en la vida. Tanto desmoraliza a sus compañeros que deciden apilar objetos esenciales para ellos con el fin de demostrarle que hay cosas que dan sentido a quiénes somos. En su búsqueda arriesgarán parte de sí mismos y descubrirán que sólo al perder algo se aprecia su valor. Pero entonces puede ser demasiado tarde.",
                    pageCount = 128,
                    averageRating = 5.0,
                    imageLinks = ImageLinks(
                        smallThumbnail = "https://www.planetadelibros.com.mx/usuaris/libros/fotos/143/m_libros/142868_portada_nada_janne-teller_201506191153.jpg",
                        thumbnail = "http://books.google.com/books/content?id=opa1DgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                    ),
                    allowAnonLogging = true,
                    canonicalVolumeLink = "bla",
                    categories = listOf("Fiction", "Drama"),
                    contentVersion = "0.4.5.0.preview.3",
                    industryIdentifiers = listOf(),
                    infoLink = "https://play.google.com/store/books/details?id=opa1DgAAQBAJ&source=gbs_api",
                    language = "es",
                    maturityRating = "NOT_MATURE",
                    panelizationSummary = PanelizationSummary(
                        containsEpubBubbles = false,
                        containsImageBubbles = false
                    ),
                    previewLink = "http://books.google.com.mx/books?id=opa1DgAAQBAJ&printsec=frontcover&dq=nada+janne&hl=&cd=1&source=gbs_api",
                    printType = "bla",
                    ratingsCount = 2,
                    readingModes = ReadingModes(
                        image = true,
                        text = true
                    ),
                    subtitle = "bla"
                ),
                saleInfo = SaleInfo(
                    country = "MX",
                    buyLink = "bla",
                    isEbook = true,
                    listPrice = ListPrice(
                        amount = 169.0,
                        currencyCode = "MXN"
                    ),
                    offers = listOf(),
                    retailPrice = RetailPriceX(
                        amount = 169.0,
                        currencyCode = "MXN"
                    ),
                    saleability = "FOR_SALE"
                ),
                accessInfo = AccessInfo(
                    country = "MX",
                    viewability = "PARTIAL",
                    embeddable = true,
                    publicDomain = false,
                    textToSpeechPermission = "ALLOWED",
                    epub = Epub(
                        isAvailable = true,
                        acsTokenLink = "link"
                    ),
                    accessViewStatus = "SAMPLE",
                    pdf = Pdf(
                        isAvailable = true,
                        acsTokenLink = "link"
                    ),
                    quoteSharingAllowed = false,
                    webReaderLink = "link"
                ),
                searchInfo = SearchInfo(
                    textSnippet = "Pierre Antón deja el colegio el día que descubre que la vida no tiene sentido. Se sube a un ciruelo y declama a gritos las razones por las que nada importa en la vida."
                )
            ),
            event = {}
        ) {

        }
    }
}
