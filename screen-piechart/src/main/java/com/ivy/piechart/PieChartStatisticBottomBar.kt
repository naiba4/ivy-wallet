package com.ivy.piechart

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ivy.base.model.TransactionType
import com.ivy.design.l0_system.UI
import com.ivy.design.l0_system.style
import com.ivy.legacy.utils.navigationBarInset
import com.ivy.legacy.utils.toDensityDp
import com.ivy.resources.R
import com.ivy.wallet.ui.theme.Gradient
import com.ivy.wallet.ui.theme.GradientGreen
import com.ivy.wallet.ui.theme.White
import com.ivy.wallet.ui.theme.components.ActionsRow
import com.ivy.wallet.ui.theme.components.CloseButton
import com.ivy.wallet.ui.theme.components.IvyButton
import com.ivy.wallet.ui.theme.gradientCutBackgroundTop

@Composable
fun BoxWithConstraintsScope.PieChartStatisticBottomBar(
    type: TransactionType,
    onClose: () -> Unit,
    onAdd: (TransactionType) -> Unit,
    modifier: Modifier = Modifier,
    bottomInset: Dp = navigationBarInset().toDensityDp()
) {
    ActionsRow(
        modifier = modifier
            .align(Alignment.BottomCenter)
            .gradientCutBackgroundTop()
            .padding(bottom = bottomInset)
            .padding(bottom = 16.dp)
    ) {
        Spacer(Modifier.width(20.dp))

        CloseButton {
            onClose()
        }

        Spacer(Modifier.weight(1f))

        val isIncome = type == TransactionType.INCOME
        IvyButton(
            iconStart = R.drawable.ic_plus,
            text = if (isIncome) {
                stringResource(
                    id = R.string.add_income
                )
            } else {
                stringResource(id = R.string.add_expense)
            },
            backgroundGradient = if (isIncome) GradientGreen else Gradient.solid(UI.colors.pureInverse),
            textStyle = UI.typo.b2.style(
                color = if (isIncome) White else UI.colors.pure,
                fontWeight = FontWeight.ExtraBold
            ),
            iconTint = if (isIncome) White else UI.colors.pure
        ) {
            onAdd(type)
        }

        Spacer(Modifier.width(20.dp))
    }
}

@Preview
@Composable
private fun PreviewBottomBar() {
    com.ivy.legacy.IvyWalletPreview {
        PieChartStatisticBottomBar(
            type = TransactionType.INCOME,
            bottomInset = 16.dp,
            onAdd = {},
            onClose = {}
        )
    }
}
