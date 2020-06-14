package com.example.jetpack.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * ================================================
 * @author xiefeipeng
 * 版    本：1.0
 * 创建日期：2020-06-13-23:09
 * 描    述：抽象类用于网络请求回调
 * 修订历史：接口和抽象类的概念不一样。接口是对动作的抽象，抽象类是对根源的抽象。
 *         抽象类表示的是，这个对象是什么。接口表示的是，这个对象能做什么。
 *         比如，男人，女人，这两个类（如果是类的话……），他们的抽象类是人。说明，他们都是人。
 *         人可以吃东西，狗也可以吃东西，你可以把“吃东西”定义成一个接口，然后让这些类去实现它.
 *         所以，在高级语言上，一个类只能继承一个类（抽象类）(正如人不可能同时是生物和非生物)，
 *         但是可以实现多个接口(吃饭接口、走路接口)。
 *         总结几句话来说：
 *
 * 1、抽象类和接口都不能直接实例化，如果要实例化，抽象类变量必须指向实现所有抽象方法的子类对象，接口变量必须指向实现所有接口方法的类对象。
 * 2、抽象类要被子类继承，接口要被类实现。
 * 3、接口只能做方法申明，抽象类中可以做方法申明，也可以做方法实现
 * 4、接口里定义的变量只能是公共的静态的常量，抽象类中的变量是普通变量。
 * 5、抽象类里的抽象方法必须全部被子类所实现，如果子类不能全部实现父类抽象方法，那么该子类只能是抽象类。同样，一个实现接口的时候，如不能全部实现接口方法，那么该类也只能为抽象类。
 * 6、抽象方法只能申明，不能实现，接口是设计的结果 ，抽象类是重构的结果
 * 7、抽象类里可以没有抽象方法
 * 8、如果一个类里有抽象方法，那么这个类只能是抽象类
 * 9、抽象方法要被实现，所以不能是静态的，也不能是私有的。
 * 10、接口可继承接口，并可多继承接口，但类只能单根继承。
 * ================================================
 */
public abstract class AbstractResultCallBack {
    /**
     * 网络请求成功的回调,回调里面具体的参数可以参照okhttp的callBack的参数来
     * @param call
     * @param response
     */
    public abstract void onSuccess(Call call, Response response);
    /**
     * 网络请求失败的回调,非抽象的，子类可实现可不实现，
     * 通常未对失败进行处理，所以没必要写成抽象，强制实现
     */
    public  void onFail(Call call, IOException e){

    };
}