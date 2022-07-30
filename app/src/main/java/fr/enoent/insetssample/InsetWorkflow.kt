package fr.enoent.insetssample

import com.squareup.workflow1.StatelessWorkflow

object InsetWorkflow : StatelessWorkflow<Unit, Nothing, InsetScreen>() {
    override fun render(renderProps: Unit, context: RenderContext): InsetScreen {
        return InsetScreen("foo")
    }
}