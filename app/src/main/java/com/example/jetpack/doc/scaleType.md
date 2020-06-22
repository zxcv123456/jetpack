八个ScaleType，其实可以分为三个类型：

以FIT_开头的4种，它们的共同点是都会对图片进行缩放；
以CENTER_开头的3种，它们的共同点是居中显示，图片的中心点会与ImageView的中心点重叠；
ScaleType.MATRIX，这种就直接翻到最后看内容吧；

ScaleType.FIT_CENTER   :默认(图片会被等比缩放到能够填充控件大小，并居中展示)
ScaleType.FIT_START    :图片等比缩放到控件大小，并放置在控件的上边或左边展示。
ScaleType.FIT_END      :图片等比缩放到控件大小，并放置在控件的下边或右边展示。
ScaleType.FIT_XY       :图片缩放到控件大小，完全填充控件大小展示。注意，此模式不是等比缩放。

ScaleType.CENTER       :不使用缩放，ImageView会展示图片的中心部分，即图片的中心点和ImageView的中心点重叠
ScaleType.CENTER_CROP  :图片会被等比缩放直到完全填充整个ImageView，并居中显示。该模式也是最常用的模式了。
ScaleType.CENTER_INSIDE:使用此模式以完全展示图片的内容为目的。图片将被等比缩放到能够完整展示在ImageView中并居中，如果图片大小小于控件大小，那么就直接居中展示该图片

ScaleType.MATRIX       :
在这八种ScaleType中，这个模式就是重点了。该模式需要与ImageView.setImageMatrix(Matrix matrix) 配合使用，
因为该模式需要用于指定一个变换矩阵用于指定图片如何展示。
其实前面的7种模式都是通过ImageView在内部生成了相应的变换矩阵，
等于是提供了该模式的一种特定值，使用这个模式只要传入相应矩阵，
也就能实现上述七种显示效果。
关于如何使用矩阵的内容，不是很快能说完，所以这里就不说了。
另外注意，在使用时，需要先调用
imageView.setScaleType(ImageView.ScaleType.MATRIX);
再调用imageView.setImageMatrix(matrix);

例子：
imageView.setScaleType(ImageView.ScaleType.MATRIX);  //设置为矩阵模式
Matrix matrix = new Matrix();           //创建一个单位矩阵
matrix.setTranslate(100, 100);          //平移x和y各100单位
matrix.preRotate(30);                   //顺时针旋转30度
imageView.setImageMatrix(matrix);       //设置并应用矩阵
