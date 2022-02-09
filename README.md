# 瓜皮AI #
2022.02.09
多年前版本并未完全完成,重构了一个包含demo的版本

通过json配置驱动  cn.rookiex.tree.TestTree.json  其中配置了一个有钱有精力就去看电影,没精力就回家睡觉恢复,没钱就去工地打工的ai,以横轴坐标示意三个地方的位置

    // |0-1-2-3-4-5-6-7-8-9-10----------------|
    // |家-1-2-书店-4-电影院-6-公园-8-9-10----------------|

node包下面包含各个类型节点,基本的选择,顺序,还有条件节点,以及简化条件选择配置用的AOtherwiseBNode

logic下面包含各个行为节点和判断节点,例如移动行为Goto,位置判定IsHere

       101 from 0 to 5 ,电影院
       101 from 1 to 5 ,电影院
       101 from 2 to 5 ,电影院
       101 from 3 to 5 ,电影院
       101 from 4 to 5 ,电影院
       101 开始看电影, 还有money 30
       101 看电影 2
       101 看电影 3
       101 失去money 20
       101 失去energy 10
       101 from 5 to 0 ,回家休息
       101 from 4 to 0 ,回家休息
       101 from 3 to 0 ,回家休息
       101 from 2 to 0 ,回家休息
       101 from 1 to 0 ,回家休息
       101 获得energy 60
       101 from 0 to 7 ,工地打工
       101 from 1 to 7 ,工地打工
       101 from 2 to 7 ,工地打工
       101 from 3 to 7 ,工地打工
       101 from 4 to 7 ,工地打工
       101 from 5 to 7 ,工地打工
       101 from 6 to 7 ,工地打工
       101 获得money 30
       101 失去energy 60
       101 from 7 to 5 ,电影院
       101 from 6 to 5 ,电影院
       101 开始看电影, 还有money 40
       101 看电影 2
       101 看电影 3
       101 失去money 20
       101 失去energy 10
       101 开始看电影, 还有money 20
       101 看电影 2
       101 看电影 3
       101 失去money 20
       101 失去energy 10
       101 from 5 to 0 ,回家休息
       101 from 4 to 0 ,回家休息
       101 from 3 to 0 ,回家休息
       101 from 2 to 0 ,回家休息
       101 from 1 to 0 ,回家休息
       101 获得energy 60
       101 from 0 to 7 ,工地打工
       101 from 1 to 7 ,工地打工
       101 from 2 to 7 ,工地打工
       101 from 3 to 7 ,工地打工
       101 from 4 to 7 ,工地打工
       101 from 5 to 7 ,工地打工
       101 from 6 to 7 ,工地打工
       101 获得money 30
       101 失去energy 60
       101 from 7 to 5 ,电影院
       101 from 6 to 5 ,电影院
       101 开始看电影, 还有money 30
       101 看电影 2
       101 看电影 3
       101 失去money 20
       101 失去energy 10
       101 from 5 to 0 ,回家休息
       Disconnected from the target VM, address: '127.0.0.1:9088', transport: 'socket'


-----------------------------------
以下为原readme


打算写一个基于行为树实现的ai.关于AI的相关介绍,使用了一些工作相关的资料,所以稍微处理了一下,不过内容就有些不通乐,大概理解一下吧,这部分主要是关于ai行为树的介绍,实际实现的时候还有一些区别

# AI设计 #
## 有限状态机 ##
有限状态机作为项目早期的ai实现,引擎中仍完整保留了有限状态机的代码,并且在部分游戏中使用的是这套ai代码,比如xxx就使用的这套代码设计.
### 说明 ###
在游戏原有的设计中,游戏ai相对简单,怪物基本上只有巡逻,被攻击,反击,追击,超出攻击范围时返回原地几个状态.通过不同的事件触发,在不同的状态之间切换.

对于游戏中大部分AI来说这个设计还是符合要求的,但是遇到需要实现的ai稍微复杂的情况,有限状态机就会显得很繁琐,同时遇到ai需要扩展的时候,扩展也比较麻烦,因为需要改动原有代码,并且维护的状态量会增加.

从上面的缺点来说,有限状态机确实不是很好,这部分的设计完全可以放弃不用了.

## AI行为树 ##
AI行为树,在公司另一个项目中已经有实现,相对于有限状态机的设计,行为树在扩展性上有更优越的性能
### 简介 ###
ai行为树从树的根节点开始遍历,一个节点一个节点检查,根据节点的信息决定操作,执行完成以后经过一定时间再次遍历.

行为树节点大概分为
- 选择节点,从左到右执行子节点,节点返回失败,执行后面的节点,直到一个节点返回成功或者运行中,向父节点返回成功或者运行中.如果所有子节点返回失败,向父节点返回失败.
- 顺序节点,从左到右执行所有节点,如果所有节点返回成功,向父节点返回成功,如果有节点返回失败或运行中,向父节点返回失败或运行中.
- 条件节点,作为选择节点和顺序节点的子节点,通过判断返回true或者false.
- 执行节点,同样作为选择节点和顺序节点的子节点,执行某一项具体的任务.

### 具体的例子,如自动攻击玩家的ai行为树设计: ###

![](http://i.imgur.com/BfRQqTa.jpg)

以上图AI实现为例,如果要新完成这个ai,需要在程序里面实现寻找目标的方法,判断目标是否存活的方法,判断目标是否在攻击范围的方法,攻击目标和追击目标的方法.

在一些需要扩展的活动中,例如攻城战中,更改ai寻找目标的方法,同一个ai_tree就可以实现,攻击攻方玩家不攻击守方玩家的逻辑.

一些相对复杂的ai也可以通过拆解为ai_tree的方式来实现

## 代码实现 ##
在代码的实现上,ai_tree主要分为对配置的解析,ai_tree执行逻辑和具体ai逻辑的实现.
### 配置解析和ai_tree的逻辑实现 ###
ai_tree的配置文件可以采用的形式,比较常见的是json和xml,解析部分已经在项目中实现.项目中目前使用json形式来配置ai_tree,这部分相对基础.

另一部分是ai_tree执行逻辑的实现,这部分主要是对ai_tree各种节点进行分析,将选择节点,顺序节点,执行节点和判断节点进行抽象,执行的时候遇到不同的节点执行不同的逻辑+

### 逻辑功能实现 ###

由于,前一部分都已经实现了,在没有出现大的需求改动的情况下,实现新的怪物ai时,只需要完成新的逻辑部分,以上为例只需要完成攻击,追击,判断目标存在,是否在攻击范围部分的逻辑.

## 策划配置 ##
理论上,只要实现了ai的各个逻辑,ai只需要交给策划配置就可以了,目前配置的形式是json,但是个人认为xml的表现可能比json更好,相比较于json,xml在比较复杂的数据关系上更加直观.

如果有时间,将ai_tree解析的部分可以更新为xml的形式.另外对于ai部分,下面是以自动攻击玩家为例的两种配置方式实例:


	json形式的自动攻击ai
	{
		class : Selector,
		children :[
		{
			class : Sequence,
			children : [
			{
				class : MonsterSearchTarget
			},
			{
				class : Sequence,
				
				children : [
				{
					class : TargetIsAlive
				},
				{
					class : Selector,
					children : [
						{
							class : Sequence,
							children : [
								{
									class : TargetIsInAttackRange
								},
								{
									class : MonsterAttack
								}
							]
						},
						{
							class : MonsterChase
						}
					]
				}
				]
			}
			]
		}
		]
	}


	xml配置方式
	<select name="自动攻击ai">
		<Sequence name="是否有人">
			<do neme = "do" class = "MonsterSearchTarget" />
		</Sequence>
		<Sequence name = "攻击目标">
			<is name = "目标是否存活" class = "TargetIsAlive"/>
			<Sequence name="攻击目标">
				<Sequence name="攻击目标">
					<is neme = "目标在攻击范围" class = "TargetIsAlive"/>
					<do name = "攻击" class = "MonsterAttack"/>
				</Sequence>
				<do name = "追击" class = "MonsterChase"/>
			</Sequence>
		</Sequence>
	</select>