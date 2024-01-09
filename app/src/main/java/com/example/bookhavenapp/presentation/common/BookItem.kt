package com.example.bookhavenapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
import com.example.bookhavenapp.presentation.common.Dimensions.BookItemHeight
import com.example.bookhavenapp.presentation.common.Dimensions.BookItemWidth
import com.example.bookhavenapp.presentation.common.Dimensions.ExtraSmallPadding
import com.example.bookhavenapp.ui.theme.BookHavenAppTheme

@Composable
fun BookItem(
    modifier: Modifier = Modifier,
    item: Item,
    onClick:() -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .height(BookItemHeight)
                .width(BookItemWidth)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest
                .Builder(context)
                .data(item.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://"))
                .build(),
            error = painterResource(id = R.drawable.placeholder),
            contentDescription = "Book Cover"
        )

        Spacer(modifier = Modifier.height(5.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val titleText = item.volumeInfo.authors?.firstOrNull() ?: "Unknown Author"

            Text(
                text = titleText,
                fontSize = 12.sp,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                color = colorResource(id = R.color.text_medium),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.volumeInfo.title,
                textAlign = TextAlign.Center,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BookItemPreview() {
    BookHavenAppTheme {
        BookItem(
            item = Item(
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
                    categories = listOf("Fiction"),
                    contentVersion = "0.4.5.0.preview.3",
                    industryIdentifiers = listOf(),
                    infoLink = "bla",
                    language = "es",
                    maturityRating = "NOT_MATURE",
                    panelizationSummary = PanelizationSummary(
                        containsEpubBubbles = false,
                        containsImageBubbles = false
                    ),
                    previewLink = "bla",
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
            )
        ) {

        }
    }
}
