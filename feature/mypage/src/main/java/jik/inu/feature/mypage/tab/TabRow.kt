package jik.inu.feature.mypage.tab

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun MyPageTabRow(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    selectedTabIndex: Int,
    onChangeSelectedTabIndex: (Int) -> Unit
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = TabType.Selected.indicatorColor
            )
        },
        divider = {
            Divider(color = TabType.UnSelected.indicatorColor)
        }
    ) {
        tabs.forEachIndexed { index, title ->
            val selected = selectedTabIndex == index
            Tab(
                selected = selected,
                onClick = { onChangeSelectedTabIndex(index) },
                selectedContentColor = TabType.Selected.contentColor,
                unselectedContentColor = TabType.UnSelected.contentColor,
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = title,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TabRowPreview() {
    MyPageTabRow(
        tabs = listOf("tab", "tab", "tab"),
        selectedTabIndex = 0,
        onChangeSelectedTabIndex = { }
    )
}