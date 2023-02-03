package com.example.alfaomega.graph

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.alfaomega.ONE_RENDER_GRAPH
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot


@Composable
fun SampleLineGraph(lines: List<List<DataPoint>>, selectGraph: Int = 0) {
    if(selectGraph == 0){
        LineGraph(
            plot = LinePlot(
                listOf(
                    LinePlot.Line(
                        lines[0],
                        LinePlot.Connection(color = MaterialTheme.colorScheme.primary),
                        LinePlot.Intersection(color = MaterialTheme.colorScheme.primary),
                        LinePlot.Highlight(color = MaterialTheme.colorScheme.primary),
                        LinePlot.AreaUnderLine(color = MaterialTheme.colorScheme.primary, 0.3f)
                    ),
                    LinePlot.Line(
                        lines[1],
                        LinePlot.Connection(color = MaterialTheme.colorScheme.error),
                        LinePlot.Intersection(color = MaterialTheme.colorScheme.error),
                        LinePlot.Highlight(color = MaterialTheme.colorScheme.error),
                        LinePlot.AreaUnderLine(color = MaterialTheme.colorScheme.error, 0.3f)
                    ),
                    LinePlot.Line(
                        lines[2],
                        LinePlot.Connection(color = MaterialTheme.colorScheme.onPrimary),
                        LinePlot.Intersection(color = MaterialTheme.colorScheme.onPrimary),
                        LinePlot.Highlight(color = MaterialTheme.colorScheme.onPrimary),
                        LinePlot.AreaUnderLine(color = MaterialTheme.colorScheme.onPrimary, 0.3f)
                    )
                ),
                grid = LinePlot.Grid(Color.Transparent, steps = 1),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            onSelection = { xLine, points ->
                // Do whatever you want here
            }
        )
    }
    else if(selectGraph == 1){
        LineGraph(
            plot = LinePlot(
                listOf(
                    LinePlot.Line(
                        lines[0],
                        LinePlot.Connection(color = MaterialTheme.colorScheme.primary),
                        LinePlot.Intersection(color = MaterialTheme.colorScheme.primary),
                        LinePlot.Highlight(color = MaterialTheme.colorScheme.primary),
                        LinePlot.AreaUnderLine(color = MaterialTheme.colorScheme.primary, 0.3f)
                    )
                ),
                grid = LinePlot.Grid(Color.Transparent, steps = 1),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            onSelection = { xLine, points ->
                // Do whatever you want here
            }
        )
    }
    else if(selectGraph == 2){
        LineGraph(
            plot = LinePlot(
                listOf(
                    LinePlot.Line(
                        lines[1],
                        LinePlot.Connection(color = MaterialTheme.colorScheme.error),
                        LinePlot.Intersection(color = MaterialTheme.colorScheme.error),
                        LinePlot.Highlight(color = MaterialTheme.colorScheme.error),
                        LinePlot.AreaUnderLine(color = MaterialTheme.colorScheme.error, 0.3f)
                    ),
                ),
                grid = LinePlot.Grid(Color.Transparent, steps = 1),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            onSelection = { xLine, points ->
                // Do whatever you want here
            }
        )
    }
    else if(selectGraph == 3){
        LineGraph(
            plot = LinePlot(
                listOf(
                    LinePlot.Line(
                        lines[2],
                        LinePlot.Connection(color = MaterialTheme.colorScheme.onPrimary),
                        LinePlot.Intersection(color = MaterialTheme.colorScheme.onPrimary),
                        LinePlot.Highlight(color = MaterialTheme.colorScheme.onPrimary),
                        LinePlot.AreaUnderLine(color = MaterialTheme.colorScheme.onPrimary, 0.3f)
                    )
                ),
                grid = LinePlot.Grid(Color.Transparent, steps = 1),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            onSelection = { xLine, points ->
                // Do whatever you want here
            }
        )
    }

//    ONE_RENDER_GRAPH = true

}