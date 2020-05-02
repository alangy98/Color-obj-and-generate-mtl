**这是本人科创竞赛的一部分流程的实现代码，可以读取.obj文件，并根据不同的高度为模型上不同的颜色，最终生成可以用于展示的新obj文件和mtl文件。** 

笔者需要一个非图形化，不需要人工干预的生成mtl的软件，最初的目的是给要展示的模型的不同已预先分割好的部分添加不同的颜色，便于直观展示，但苦于几乎没有已有开源库可以方便地实现类似需求，例如vtk库只能给一个actor指定一种材质，直接用贴图又无法保存为obj/mtl，只能保存为场景文件，不方便展示，因此写了这个自行解析obj文件并生成结果的程序。



版权声明：自由转载-非商用-保持署名（创意共享3.0许可证）





