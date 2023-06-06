package com.mobile.base.sampledemo.epoxy

import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.EpoxyModel
import com.mobile.base.sampledemo.R
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.util.concurrent.ConcurrentHashMap

/**
 * @author Xuan
 * @date 2023-06-06
 * <p>
 * @description
 * <p>
 */
abstract class ViewBindingKotlinModel<T : ViewBinding>(
    @LayoutRes private val layoutRes: Int
) : EpoxyModel<View>() {

    private val bindingMethod by lazy { getBindMethodFrom(this::class.java) }

    abstract fun T.bind()

    @Suppress("UNCHECKED_CAST")
    override fun bind(view: View) {
        var binding = view.getTag(R.id.epoxy_viewbinding) as? T
        if (binding == null) {
            binding = bindingMethod.invoke(null, view) as T
            view.setTag(R.id.epoxy_viewbinding, binding)
        }
        binding.bind()
    }

    override fun getDefaultLayout(): Int = layoutRes

}

private val sBindingMethodByClass = ConcurrentHashMap<Class<*>, Method>()

private fun getBindMethodFrom(javaClass: Class<*>): Method =
    sBindingMethodByClass.getOrPut(javaClass) {
        val actualTypeOfThis = getSuperClassParameterizedType(javaClass)
        val viewBindingClass = actualTypeOfThis.actualTypeArguments[0] as Class<ViewBinding>
        viewBindingClass.getDeclaredMethod("bind", View::class.java)
            ?: error("The binder class ${javaClass.canonicalName} should have a method bind(View")
    }

private fun getSuperClassParameterizedType(klass: Class<*>): ParameterizedType {
    val genericSuperClass = klass.genericSuperclass
    return (genericSuperClass as? ParameterizedType) ?: getSuperClassParameterizedType(
        genericSuperClass as Class<*>
    )
}