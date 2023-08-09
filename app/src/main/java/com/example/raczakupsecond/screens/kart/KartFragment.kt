package com.example.raczakupsecond.screens.kart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentKartBinding
import com.example.raczakupsecond.theme.bold
import com.example.raczakupsecond.theme.def
import com.example.raczakupsecond.theme.italic

class KartFragment : Fragment(R.layout.fragment_kart) {
    private lateinit var binding : FragmentKartBinding
    private val viewModel : KartFragmentVM by viewModels()

    private val TAG = KartFragment::class.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            Kart()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Kart() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Header()
        Address()
        Goods()
        UnitInfo("3,5 кг", "0 ₽", "1200 ₽")
        CommentField()
        Payment()
    }
}

@Composable
private fun Header(){
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(
                top = 10.dp
            ),
        shape = RoundedCornerShape(
            topEnd = 100.dp,
            bottomEnd = 100.dp
        ),
        backgroundColor = colorResource(
            id = R.color.background_accent_light
        ),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        bottom = 10.dp,
                        start = 20.dp,
//                            end = 50.dp
                    ),
                text = stringResource(id = R.string.kart),
                color = colorResource(id = R.color.text_primary),
                fontFamily = italic,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
private fun Address(){

    val testTitle = "Дом"
    val testAddress = "г. Москва,\n" +
            "ул. Стромынка, 36\n" +
            "кв. 1, этаж 1, подъезд 1"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp
            )
    ) {
        Text(
            text = stringResource(id = R.string.address_purchase),
            color = colorResource(id = R.color.text_primary),
            fontFamily = bold,
            fontSize = 18.sp
        )
        AddressItem(title = testTitle, address = testAddress)
    }
}

@Composable
private fun AddressItem(title: String, address: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
//                start = 20.dp,
//                end = 20.dp,
                top = 10.dp
            ),
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 15.dp,
//                    horizontal = 5.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = title,
                fontSize =  18.sp,
                fontFamily = def,
                color = colorResource(id = R.color.text_primary)
            )
            Text(
                text = address,
                fontSize = 12.sp,
                fontFamily = def,
                color = colorResource(id = R.color.text_primary)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_edit_pen_and_paper_gray),
                contentDescription = "edit pen",
                modifier = Modifier
                    .size(25.dp)
            )
        }
    }
}

@Composable
private fun Goods() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp
            )
    ) {
        Text(
            text = stringResource(id = R.string.goods),
            color = colorResource(id = R.color.text_primary),
            fontFamily = bold,
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier
                .padding(
                    top = 5.dp
                ),
            text = stringResource(id = R.string.your_pack),
            color = colorResource(id = R.color.text_primary),
            fontFamily = def,
            fontSize = 16.sp
        )
        //lazy column TODO()
        ProductItem()

        Divider(
            color = colorResource(id = R.color.light_text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp
                )
                .height(0.5.dp)
        )

        Text(
            modifier = Modifier
                .padding(
                    top = 0.dp
                ),
            text = stringResource(id = R.string.your_choice),
            color = colorResource(id = R.color.text_primary),
            fontFamily = def,
            fontSize = 16.sp
        )
        //lazy column TODO()
        ProductItem()

        Divider(
            color = colorResource(id = R.color.light_text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp
                )
                .height(0.5.dp)
        )
    }
}

@Composable
private fun ProductItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(
                top = 10.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.frozen1),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
//                .background(colorResource(id = R.color.carbohydrates))
                    .clip(RoundedCornerShape(10.dp))
//                .border(3.dp, Color.Black, RoundedCornerShape(10.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        start = 10.dp
                    ),
//                .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Text(
                        text = "Молоко Домашнее 3,2%",
                        fontFamily = def,
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.text_primary)
                    )
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 5.dp
                            ),
                        text = "(0,5л)",
                        fontFamily = def,
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.text_secondary)
                    )
                }
                Text(
                    text = "82 ₽/шт.",
                    fontFamily = bold,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.text_primary)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "246₽",
                fontFamily = bold,
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_primary)
            )
            Text(
                text = "3 шт",
                fontFamily = def,
                fontSize = 12.sp,
                color = colorResource(id = R.color.text_secondary)
            )
            Text(
                text = "1,5 л",
                fontFamily = def,
                fontSize = 12.sp,
                color = colorResource(id = R.color.text_secondary)
            )
        }
    }
}

@Composable
private fun UnitInfo(
    weight : String,
    delivery_price : String,
    all_price : String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.weight_of_order),
                fontFamily = def,
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_primary)
            )
            Text(
                text = weight,
                fontFamily = def,
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_primary)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 5.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.price_of_delivery),
                fontFamily = def,
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_primary)
            )
            Text(
                text = delivery_price,
                fontFamily = def,
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_primary)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.price_of_order),
                fontFamily = bold,
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_primary)
            )
            Text(
                text = all_price,
                fontFamily = bold,
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_primary)
            )
        }

        Divider(
            color = colorResource(id = R.color.light_text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp
                )
                .height(0.5.dp)
        )
    }
}

@Composable
private fun CommentField() {

    // for get text value of text field
    var inputValue by remember {
        mutableStateOf(TextFieldValue())
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
            },
            label = {
                Text(
                    text = stringResource(id = R.string.comment_to_order),
                    color = colorResource(id = R.color.text_secondary),
                    fontFamily = def,
                    fontSize = 14.sp
                )
            },
            modifier = Modifier
                .padding(
                    horizontal = 20.dp
                )
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text
            ),
            textStyle = TextStyle(
                color = colorResource(id = R.color.text_primary),
                fontSize = 14.sp,
                fontFamily = def
            ),
//            maxLines = 2,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.background_secondary),
                cursorColor = colorResource(id = R.color.text_accent),
                textColor = colorResource(id = R.color.text_primary),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
//                focusedLabelColor = colorResource(id = R.color.text_accent),
//                unfocusedLabelColor = colorResource(id = R.color.text_accent)
            )
        )
        Divider(
            color = colorResource(id = R.color.light_text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp,
                    horizontal = 20.dp
                )
                .height(0.5.dp)
        )
    }
}

@Composable
private fun Payment() {
    Column(
        modifier = Modifier
            .padding(
                horizontal = 20.dp
            )
    ) {
        Text(
            text = stringResource(id = R.string.payment_variant),
            color = colorResource(id = R.color.text_primary),
            fontFamily = bold,
            fontSize = 18.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_credit_card),
                    contentDescription = "credit card icon"
                )
                Text(
                    text = "**** **** **** 6264",
                    modifier = Modifier
                    .padding(
                        start = 20.dp
                    ),
                    fontFamily = def,
//                    letterSpacing = 1.sp,
                    fontSize = 14.sp
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_edit_pen_and_paper_gray),
                contentDescription = "edit pen",
                modifier = Modifier
                    .size(25.dp)
            )
        }
    }
}