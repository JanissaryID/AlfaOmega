package com.example.alfaomega.graph

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot


@Composable
fun SampleLineGraph(lines: List<DataPoint>) {
    LineGraph(
        plot = LinePlot(
            listOf(
                LinePlot.Line(
                    lines,
                    LinePlot.Connection(color = MaterialTheme.colorScheme.primary),
                    LinePlot.Intersection(color = MaterialTheme.colorScheme.primary),
                    LinePlot.Highlight(color = MaterialTheme.colorScheme.primary),
                    LinePlot.AreaUnderLine(color = MaterialTheme.colorScheme.primary, 0.3f)
                )
            ),
            grid = LinePlot.Grid(Color.Transparent, steps = 1),
        ),
        modifier = Modifier.fillMaxWidth().height(200.dp),
        onSelection = { xLine, points ->
            // Do whatever you want here
        }
    )
}