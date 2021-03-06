/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.navigationToolbar

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.KotlinIconProvider
import org.jetbrains.kotlin.idea.projectView.KtDeclarationTreeNode.Companion.tryGetRepresentableText
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtFile

// FIX ME WHEN BUNCH 201 REMOVED
class KotlinNavBarModelExtension : AbstractNavBarModelExtensionCompatBase() {
    override fun getPresentableText(item: Any?): String? =
        (item as? KtDeclaration)?.let { tryGetRepresentableText(it, it.project) }

    override fun adjustElementImpl(psiElement: PsiElement?): PsiElement? {
        val containingFile = psiElement?.containingFile as? KtFile ?: return psiElement
        if (containingFile.isScript()) return psiElement
        return KotlinIconProvider.getSingleClass(containingFile) ?: psiElement
    }
}