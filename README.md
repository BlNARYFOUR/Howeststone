<h1>Howeststone</h1>
<h2>Introduction</h2>

We have created our own version of Hearthstone for the module Project I.<br/>
We call it 'HowestStone'.

<h3>Three Bees and Me (groep 31)</h3>

* Mattijs Step (Oranje) I'm gonna catch them all.
* Bert Van Mulders (Geel) I am one with the force and the force is with me.
* Brend Lambert (Groen) Do what I do, Hold tight and pretend it's a plan!
* Bram Vermeulen (Roze) That's what i do. I drink and I know things.

![alt text](PMDOC/groepsfoto.jpg "Groepsfoto 3BEES&ME")

<h2>How to play</h2>
<h3>How to create the database</h3>
* Start your WAMP server
* In java, open the class SetupDatabase in the package setupdatabase
* Now click run

<h3>How to start the server</h3>
* Open the package api
* Open the class Server.java
* Now click run

<h3>How to view the webpage</h3>
* First follow the steps above to start the server
* Then click "localhost:4242" in the commandprompt, or enter this in your browser
* Now enjoy the game!

<h3>How to save and open the CLI</h3>
* Saving of the game is NYI
* Run the java console version by running console/Display.java
* This console application is not yet fully implemented

<h2>Diagrams</h2>
<h3>Java</h3>
![alt text](documents/Java/javaUML.png "modelJavaClasses")

<h3>Database</h3>
![alt text](documents/Database/modelDatabase.PNG "modelDatabase")

<html>
<head>
<title>Schema for database 'howeststone'</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<style type="text/css">

</style>
</head>
<body bgcolor='#ffffff' topmargin="0">
<table width="100%" border="0" cellspacing="0" cellpadding="5">
<tr>
<td class="toptext"><p align="center">howeststone</td>
</tr>
</table>
<a name="header"></a><ul>
	<li><a href="#abilities"><p class="normal">abilities</a></li>
	<li><a href="#cardabilities"><p class="normal">cardabilities</a></li>
	<li><a href="#cardmechanics"><p class="normal">cardmechanics</a></li>
	<li><a href="#cards"><p class="normal">cards</a></li>
	<li><a href="#cardsindecks"><p class="normal">cardsindecks</a></li>
	<li><a href="#decks"><p class="normal">decks</a></li>
	<li><a href="#heroes"><p class="normal">heroes</a></li>
	<li><a href="#mechanics"><p class="normal">mechanics</a></li>
</ul>
<br class=page>
<p><a name='abilities'></a>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="headtext" width="30%" align="left" valign="top">abilities</td>
	<td></td>
<tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Fields</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="center" valign="top" class="fieldcolumn">Field</td>
	<td align="center" valign="top" class="fieldcolumn">Type</td>
	<td align="center" valign="top" class="fieldcolumn">Collation</td>
	<td align="center" valign="top" class="fieldcolumn">Null</td>
	<td align="center" valign="top" class="fieldcolumn">Key</td>
	<td align="center" valign="top" class="fieldcolumn">Default</td>
	<td align="center" valign="top" class="fieldcolumn">Extra</td>
	<td align="center" valign="top" class="fieldcolumn">Privileges</td>
	<td align="center" valign="top" class="fieldcolumn">Comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">abilityId</td>
	<td align="left" valign="top"><p class="normal">int(3)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">PRI</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">auto_increment</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">abilityName</td>
	<td align="left" valign="top"><p class="normal">varchar(45)</td>
	<td align="left" valign="top"><p class="normal">latin1_swedish_ci</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Indexes</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">Table</td>
	<td align="left" valign="top" class="fieldcolumn">Non<br>unique</td>
	<td align="left" valign="top" class="fieldcolumn">Key<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Seq<br>in<br>index</td>
	<td align="left" valign="top" class="fieldcolumn">Column<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Collation</td>
	<td align="left" valign="top" class="fieldcolumn">Cardinality</td>
	<td align="left" valign="top" class="fieldcolumn">Sub<br>part</td>
	<td align="left" valign="top" class="fieldcolumn">Packed</td>
	<td align="left" valign="top" class="fieldcolumn">Null</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>type</td>
	<td align="left" valign="top" class="fieldcolumn">Comment</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">abilities</td>
	<td align="left" valign="top"><p class="normal">0</td>
	<td align="left" valign="top"><p class="normal">PRIMARY</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">abilityId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">3</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<a href="#header"><p class="normal">Back</a><br class=page>
<p><a name='cardabilities'></a>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="headtext" width="30%" align="left" valign="top">cardabilities</td>
	<td></td>
<tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Fields</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="center" valign="top" class="fieldcolumn">Field</td>
	<td align="center" valign="top" class="fieldcolumn">Type</td>
	<td align="center" valign="top" class="fieldcolumn">Collation</td>
	<td align="center" valign="top" class="fieldcolumn">Null</td>
	<td align="center" valign="top" class="fieldcolumn">Key</td>
	<td align="center" valign="top" class="fieldcolumn">Default</td>
	<td align="center" valign="top" class="fieldcolumn">Extra</td>
	<td align="center" valign="top" class="fieldcolumn">Privileges</td>
	<td align="center" valign="top" class="fieldcolumn">Comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">abilityId</td>
	<td align="left" valign="top"><p class="normal">int(3)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">PRI</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardId</td>
	<td align="left" valign="top"><p class="normal">int(3)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">PRI</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardMechId</td>
	<td align="left" valign="top"><p class="normal">int(3)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">PRI</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Indexes</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">Table</td>
	<td align="left" valign="top" class="fieldcolumn">Non<br>unique</td>
	<td align="left" valign="top" class="fieldcolumn">Key<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Seq<br>in<br>index</td>
	<td align="left" valign="top" class="fieldcolumn">Column<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Collation</td>
	<td align="left" valign="top" class="fieldcolumn">Cardinality</td>
	<td align="left" valign="top" class="fieldcolumn">Sub<br>part</td>
	<td align="left" valign="top" class="fieldcolumn">Packed</td>
	<td align="left" valign="top" class="fieldcolumn">Null</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>type</td>
	<td align="left" valign="top" class="fieldcolumn">Comment</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardabilities</td>
	<td align="left" valign="top"><p class="normal">0</td>
	<td align="left" valign="top"><p class="normal">PRIMARY</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">abilityId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">30</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardabilities</td>
	<td align="left" valign="top"><p class="normal">0</td>
	<td align="left" valign="top"><p class="normal">PRIMARY</td>
	<td align="left" valign="top"><p class="normal">2</td>
	<td align="left" valign="top"><p class="normal">cardId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">117</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardabilities</td>
	<td align="left" valign="top"><p class="normal">0</td>
	<td align="left" valign="top"><p class="normal">PRIMARY</td>
	<td align="left" valign="top"><p class="normal">3</td>
	<td align="left" valign="top"><p class="normal">cardMechId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">158</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardabilities</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">fk_CardAbilities2</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">cardId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">105</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardabilities</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">fk_CardAbilities3</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">cardMechId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">132</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Foreign Key Relationships</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">FK Id</td>
	<td align="left" valign="top" class="fieldcolumn">Reference Table</td>
	<td align="left" valign="top" class="fieldcolumn">Source Column</td>
	<td align="left" valign="top" class="fieldcolumn">Target Column</td>
	<td align="left" valign="top" class="fieldcolumn">Extra Info</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">fk_CardAbilities</td>
	<td align="left" valign="top"><p class="normal">abilities</td>
	<td align="left" valign="top"><p class="normal">`abilityId`</td>
	<td align="left" valign="top"><p class="normal">`abilityId`</td>
	<td align="left" valign="top"><p class="normal">,</td>
<tr>
<tr>
	<td align="left" valign="top"><p class="normal">fk_CardAbilities2</td>
	<td align="left" valign="top"><p class="normal">cards</td>
	<td align="left" valign="top"><p class="normal">`cardId`</td>
	<td align="left" valign="top"><p class="normal">`cardId`</td>
	<td align="left" valign="top"><p class="normal">,</td>
<tr>
<tr>
	<td align="left" valign="top"><p class="normal">fk_CardAbilities3</td>
	<td align="left" valign="top"><p class="normal">cardmechanics</td>
	<td align="left" valign="top"><p class="normal">`cardMechId`</td>
	<td align="left" valign="top"><p class="normal">`cardMechId`</td>
<tr>
</table>


<a href="#header"><p class="normal">Back</a><br class=page>
<p><a name='cardmechanics'></a>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="headtext" width="30%" align="left" valign="top">cardmechanics</td>
	<td></td>
<tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Fields</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="center" valign="top" class="fieldcolumn">Field</td>
	<td align="center" valign="top" class="fieldcolumn">Type</td>
	<td align="center" valign="top" class="fieldcolumn">Collation</td>
	<td align="center" valign="top" class="fieldcolumn">Null</td>
	<td align="center" valign="top" class="fieldcolumn">Key</td>
	<td align="center" valign="top" class="fieldcolumn">Default</td>
	<td align="center" valign="top" class="fieldcolumn">Extra</td>
	<td align="center" valign="top" class="fieldcolumn">Privileges</td>
	<td align="center" valign="top" class="fieldcolumn">Comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardMechId</td>
	<td align="left" valign="top"><p class="normal">int(3)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">PRI</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">auto_increment</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">mechanicId</td>
	<td align="left" valign="top"><p class="normal">int(3)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">MUL</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">target</td>
	<td align="left" valign="top"><p class="normal">varchar(45)</td>
	<td align="left" valign="top"><p class="normal">latin1_swedish_ci</td>
	<td align="left" valign="top"><p class="normal">YES</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">mechValue</td>
	<td align="left" valign="top"><p class="normal">varchar(45)</td>
	<td align="left" valign="top"><p class="normal">latin1_swedish_ci</td>
	<td align="left" valign="top"><p class="normal">YES</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Indexes</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">Table</td>
	<td align="left" valign="top" class="fieldcolumn">Non<br>unique</td>
	<td align="left" valign="top" class="fieldcolumn">Key<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Seq<br>in<br>index</td>
	<td align="left" valign="top" class="fieldcolumn">Column<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Collation</td>
	<td align="left" valign="top" class="fieldcolumn">Cardinality</td>
	<td align="left" valign="top" class="fieldcolumn">Sub<br>part</td>
	<td align="left" valign="top" class="fieldcolumn">Packed</td>
	<td align="left" valign="top" class="fieldcolumn">Null</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>type</td>
	<td align="left" valign="top" class="fieldcolumn">Comment</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardmechanics</td>
	<td align="left" valign="top"><p class="normal">0</td>
	<td align="left" valign="top"><p class="normal">PRIMARY</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">cardMechId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">118</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardmechanics</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">fk_cardMechanics</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">mechanicId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">24</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Foreign Key Relationships</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">FK Id</td>
	<td align="left" valign="top" class="fieldcolumn">Reference Table</td>
	<td align="left" valign="top" class="fieldcolumn">Source Column</td>
	<td align="left" valign="top" class="fieldcolumn">Target Column</td>
	<td align="left" valign="top" class="fieldcolumn">Extra Info</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">fk_cardMechanics</td>
	<td align="left" valign="top"><p class="normal">mechanics</td>
	<td align="left" valign="top"><p class="normal">`mechanicId`</td>
	<td align="left" valign="top"><p class="normal">`mechanicId`</td>
<tr>
</table>


<a href="#header"><p class="normal">Back</a><br class=page>
<p><a name='cards'></a>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="headtext" width="30%" align="left" valign="top">cards</td>
	<td></td>
<tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Fields</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="center" valign="top" class="fieldcolumn">Field</td>
	<td align="center" valign="top" class="fieldcolumn">Type</td>
	<td align="center" valign="top" class="fieldcolumn">Collation</td>
	<td align="center" valign="top" class="fieldcolumn">Null</td>
	<td align="center" valign="top" class="fieldcolumn">Key</td>
	<td align="center" valign="top" class="fieldcolumn">Default</td>
	<td align="center" valign="top" class="fieldcolumn">Extra</td>
	<td align="center" valign="top" class="fieldcolumn">Privileges</td>
	<td align="center" valign="top" class="fieldcolumn">Comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardId</td>
	<td align="left" valign="top"><p class="normal">int(3)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">PRI</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">auto_increment</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardName</td>
	<td align="left" valign="top"><p class="normal">varchar(45)</td>
	<td align="left" valign="top"><p class="normal">latin1_swedish_ci</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardType</td>
	<td align="left" valign="top"><p class="normal">varchar(45)</td>
	<td align="left" valign="top"><p class="normal">latin1_swedish_ci</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">race</td>
	<td align="left" valign="top"><p class="normal">varchar(45)</td>
	<td align="left" valign="top"><p class="normal">latin1_swedish_ci</td>
	<td align="left" valign="top"><p class="normal">YES</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">img</td>
	<td align="left" valign="top"><p class="normal">varchar(100)</td>
	<td align="left" valign="top"><p class="normal">latin1_swedish_ci</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">rarity</td>
	<td align="left" valign="top"><p class="normal">varchar(45)</td>
	<td align="left" valign="top"><p class="normal">latin1_swedish_ci</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">health</td>
	<td align="left" valign="top"><p class="normal">int(2)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">YES</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">attack</td>
	<td align="left" valign="top"><p class="normal">int(2)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">YES</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">manaCost</td>
	<td align="left" valign="top"><p class="normal">int(2)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">durability</td>
	<td align="left" valign="top"><p class="normal">int(2)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">YES</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">heroId</td>
	<td align="left" valign="top"><p class="normal">int(2)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">YES</td>
	<td align="left" valign="top"><p class="normal">MUL</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Indexes</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">Table</td>
	<td align="left" valign="top" class="fieldcolumn">Non<br>unique</td>
	<td align="left" valign="top" class="fieldcolumn">Key<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Seq<br>in<br>index</td>
	<td align="left" valign="top" class="fieldcolumn">Column<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Collation</td>
	<td align="left" valign="top" class="fieldcolumn">Cardinality</td>
	<td align="left" valign="top" class="fieldcolumn">Sub<br>part</td>
	<td align="left" valign="top" class="fieldcolumn">Packed</td>
	<td align="left" valign="top" class="fieldcolumn">Null</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>type</td>
	<td align="left" valign="top" class="fieldcolumn">Comment</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cards</td>
	<td align="left" valign="top"><p class="normal">0</td>
	<td align="left" valign="top"><p class="normal">PRIMARY</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">cardId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">2</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cards</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">fk_cards</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">heroId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">2</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">YES</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Foreign Key Relationships</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">FK Id</td>
	<td align="left" valign="top" class="fieldcolumn">Reference Table</td>
	<td align="left" valign="top" class="fieldcolumn">Source Column</td>
	<td align="left" valign="top" class="fieldcolumn">Target Column</td>
	<td align="left" valign="top" class="fieldcolumn">Extra Info</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">fk_cards</td>
	<td align="left" valign="top"><p class="normal">heroes</td>
	<td align="left" valign="top"><p class="normal">`heroId`</td>
	<td align="left" valign="top"><p class="normal">`heroId`</td>
<tr>
</table>


<a href="#header"><p class="normal">Back</a><br class=page>
<p><a name='cardsindecks'></a>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="headtext" width="30%" align="left" valign="top">cardsindecks</td>
	<td></td>
<tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Fields</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="center" valign="top" class="fieldcolumn">Field</td>
	<td align="center" valign="top" class="fieldcolumn">Type</td>
	<td align="center" valign="top" class="fieldcolumn">Collation</td>
	<td align="center" valign="top" class="fieldcolumn">Null</td>
	<td align="center" valign="top" class="fieldcolumn">Key</td>
	<td align="center" valign="top" class="fieldcolumn">Default</td>
	<td align="center" valign="top" class="fieldcolumn">Extra</td>
	<td align="center" valign="top" class="fieldcolumn">Privileges</td>
	<td align="center" valign="top" class="fieldcolumn">Comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">deckId</td>
	<td align="left" valign="top"><p class="normal">int(2)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">PRI</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardId</td>
	<td align="left" valign="top"><p class="normal">int(3)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">PRI</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">amount</td>
	<td align="left" valign="top"><p class="normal">int(1)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Indexes</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">Table</td>
	<td align="left" valign="top" class="fieldcolumn">Non<br>unique</td>
	<td align="left" valign="top" class="fieldcolumn">Key<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Seq<br>in<br>index</td>
	<td align="left" valign="top" class="fieldcolumn">Column<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Collation</td>
	<td align="left" valign="top" class="fieldcolumn">Cardinality</td>
	<td align="left" valign="top" class="fieldcolumn">Sub<br>part</td>
	<td align="left" valign="top" class="fieldcolumn">Packed</td>
	<td align="left" valign="top" class="fieldcolumn">Null</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>type</td>
	<td align="left" valign="top" class="fieldcolumn">Comment</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardsindecks</td>
	<td align="left" valign="top"><p class="normal">0</td>
	<td align="left" valign="top"><p class="normal">PRIMARY</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">deckId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">6</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardsindecks</td>
	<td align="left" valign="top"><p class="normal">0</td>
	<td align="left" valign="top"><p class="normal">PRIMARY</td>
	<td align="left" valign="top"><p class="normal">2</td>
	<td align="left" valign="top"><p class="normal">cardId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">6</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">cardsindecks</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">fk_CardsInDecks2</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">cardId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">6</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Foreign Key Relationships</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">FK Id</td>
	<td align="left" valign="top" class="fieldcolumn">Reference Table</td>
	<td align="left" valign="top" class="fieldcolumn">Source Column</td>
	<td align="left" valign="top" class="fieldcolumn">Target Column</td>
	<td align="left" valign="top" class="fieldcolumn">Extra Info</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">fk_CardsInDecks</td>
	<td align="left" valign="top"><p class="normal">decks</td>
	<td align="left" valign="top"><p class="normal">`deckId`</td>
	<td align="left" valign="top"><p class="normal">`deckId`</td>
	<td align="left" valign="top"><p class="normal">,</td>
<tr>
<tr>
	<td align="left" valign="top"><p class="normal">fk_CardsInDecks2</td>
	<td align="left" valign="top"><p class="normal">cards</td>
	<td align="left" valign="top"><p class="normal">`cardId`</td>
	<td align="left" valign="top"><p class="normal">`cardId`</td>
<tr>
</table>


<a href="#header"><p class="normal">Back</a><br class=page>
<p><a name='decks'></a>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="headtext" width="30%" align="left" valign="top">decks</td>
	<td></td>
<tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Fields</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="center" valign="top" class="fieldcolumn">Field</td>
	<td align="center" valign="top" class="fieldcolumn">Type</td>
	<td align="center" valign="top" class="fieldcolumn">Collation</td>
	<td align="center" valign="top" class="fieldcolumn">Null</td>
	<td align="center" valign="top" class="fieldcolumn">Key</td>
	<td align="center" valign="top" class="fieldcolumn">Default</td>
	<td align="center" valign="top" class="fieldcolumn">Extra</td>
	<td align="center" valign="top" class="fieldcolumn">Privileges</td>
	<td align="center" valign="top" class="fieldcolumn">Comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">deckId</td>
	<td align="left" valign="top"><p class="normal">int(2)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">PRI</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">auto_increment</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">deckName</td>
	<td align="left" valign="top"><p class="normal">varchar(45)</td>
	<td align="left" valign="top"><p class="normal">latin1_swedish_ci</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">heroId</td>
	<td align="left" valign="top"><p class="normal">int(2)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">MUL</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Indexes</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">Table</td>
	<td align="left" valign="top" class="fieldcolumn">Non<br>unique</td>
	<td align="left" valign="top" class="fieldcolumn">Key<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Seq<br>in<br>index</td>
	<td align="left" valign="top" class="fieldcolumn">Column<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Collation</td>
	<td align="left" valign="top" class="fieldcolumn">Cardinality</td>
	<td align="left" valign="top" class="fieldcolumn">Sub<br>part</td>
	<td align="left" valign="top" class="fieldcolumn">Packed</td>
	<td align="left" valign="top" class="fieldcolumn">Null</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>type</td>
	<td align="left" valign="top" class="fieldcolumn">Comment</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">decks</td>
	<td align="left" valign="top"><p class="normal">0</td>
	<td align="left" valign="top"><p class="normal">PRIMARY</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">deckId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">2</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">decks</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">fk_Decks</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">heroId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">2</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Foreign Key Relationships</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">FK Id</td>
	<td align="left" valign="top" class="fieldcolumn">Reference Table</td>
	<td align="left" valign="top" class="fieldcolumn">Source Column</td>
	<td align="left" valign="top" class="fieldcolumn">Target Column</td>
	<td align="left" valign="top" class="fieldcolumn">Extra Info</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">fk_Decks</td>
	<td align="left" valign="top"><p class="normal">heroes</td>
	<td align="left" valign="top"><p class="normal">`heroId`</td>
	<td align="left" valign="top"><p class="normal">`heroId`</td>
<tr>
</table>


<a href="#header"><p class="normal">Back</a><br class=page>
<p><a name='heroes'></a>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="headtext" width="30%" align="left" valign="top">heroes</td>
	<td></td>
<tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Fields</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="center" valign="top" class="fieldcolumn">Field</td>
	<td align="center" valign="top" class="fieldcolumn">Type</td>
	<td align="center" valign="top" class="fieldcolumn">Collation</td>
	<td align="center" valign="top" class="fieldcolumn">Null</td>
	<td align="center" valign="top" class="fieldcolumn">Key</td>
	<td align="center" valign="top" class="fieldcolumn">Default</td>
	<td align="center" valign="top" class="fieldcolumn">Extra</td>
	<td align="center" valign="top" class="fieldcolumn">Privileges</td>
	<td align="center" valign="top" class="fieldcolumn">Comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">heroId</td>
	<td align="left" valign="top"><p class="normal">int(2)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">PRI</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">auto_increment</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">heroName</td>
	<td align="left" valign="top"><p class="normal">varchar(45)</td>
	<td align="left" valign="top"><p class="normal">latin1_swedish_ci</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Indexes</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">Table</td>
	<td align="left" valign="top" class="fieldcolumn">Non<br>unique</td>
	<td align="left" valign="top" class="fieldcolumn">Key<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Seq<br>in<br>index</td>
	<td align="left" valign="top" class="fieldcolumn">Column<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Collation</td>
	<td align="left" valign="top" class="fieldcolumn">Cardinality</td>
	<td align="left" valign="top" class="fieldcolumn">Sub<br>part</td>
	<td align="left" valign="top" class="fieldcolumn">Packed</td>
	<td align="left" valign="top" class="fieldcolumn">Null</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>type</td>
	<td align="left" valign="top" class="fieldcolumn">Comment</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">heroes</td>
	<td align="left" valign="top"><p class="normal">0</td>
	<td align="left" valign="top"><p class="normal">PRIMARY</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">heroId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">3</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<a href="#header"><p class="normal">Back</a><br class=page>
<p><a name='mechanics'></a>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="headtext" width="30%" align="left" valign="top">mechanics</td>
	<td></td>
<tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Fields</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="center" valign="top" class="fieldcolumn">Field</td>
	<td align="center" valign="top" class="fieldcolumn">Type</td>
	<td align="center" valign="top" class="fieldcolumn">Collation</td>
	<td align="center" valign="top" class="fieldcolumn">Null</td>
	<td align="center" valign="top" class="fieldcolumn">Key</td>
	<td align="center" valign="top" class="fieldcolumn">Default</td>
	<td align="center" valign="top" class="fieldcolumn">Extra</td>
	<td align="center" valign="top" class="fieldcolumn">Privileges</td>
	<td align="center" valign="top" class="fieldcolumn">Comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">mechanicId</td>
	<td align="left" valign="top"><p class="normal">int(3)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">PRI</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">auto_increment</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">mechanicType</td>
	<td align="left" valign="top"><p class="normal">varchar(45)</td>
	<td align="left" valign="top"><p class="normal">latin1_swedish_ci</td>
	<td align="left" valign="top"><p class="normal">NO</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">select,insert,update,references</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td class="fieldheader" width="100%" align="left" valign="top">Indexes</td>
</tr>
</table>
<table width="100%" cellspacing="0" cellapdding="2" border="1">
<tr>
	<td align="left" valign="top" class="fieldcolumn">Table</td>
	<td align="left" valign="top" class="fieldcolumn">Non<br>unique</td>
	<td align="left" valign="top" class="fieldcolumn">Key<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Seq<br>in<br>index</td>
	<td align="left" valign="top" class="fieldcolumn">Column<br>name</td>
	<td align="left" valign="top" class="fieldcolumn">Collation</td>
	<td align="left" valign="top" class="fieldcolumn">Cardinality</td>
	<td align="left" valign="top" class="fieldcolumn">Sub<br>part</td>
	<td align="left" valign="top" class="fieldcolumn">Packed</td>
	<td align="left" valign="top" class="fieldcolumn">Null</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>type</td>
	<td align="left" valign="top" class="fieldcolumn">Comment</td>
	<td align="left" valign="top" class="fieldcolumn">Index<br>comment</td>
</tr>
<tr>
	<td align="left" valign="top"><p class="normal">mechanics</td>
	<td align="left" valign="top"><p class="normal">0</td>
	<td align="left" valign="top"><p class="normal">PRIMARY</td>
	<td align="left" valign="top"><p class="normal">1</td>
	<td align="left" valign="top"><p class="normal">mechanicId</td>
	<td align="left" valign="top"><p class="normal">A</td>
	<td align="left" valign="top"><p class="normal">2</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">(NULL)</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">BTREE</td>
	<td align="left" valign="top"><p class="normal">;</td>
	<td align="left" valign="top"><p class="normal">;</td>
</tr>
</table>

<a href="#header"><p class="normal">Back</a>
<h1 width="100%"></body>
</html>

<h2>Progress</h2>
<h3>Front-end</h3>
<h4>Implemented</h4>
* Gameboard:
    * Hero health
    * Basic mana
    * Attack/Health/Durability/Mana cost for the cards
    * Playing cards
    * Replace cards
    * Fan cards
    * End turn
    * Replace cards
    * enemy mana
* Deckbuilder (fundamentals)
* Menu's:
    * Main menu
    * Options
    * Menubar
    * Hero selection menu
    * Deck Chooser menu
* Credits screen
* Background music
* Toggle fullscreen

<h4>Not Yet Implemented</h4>
* Search function deckbuilder
* Current game can be saved from the front-end side
* Battlelog
* tutorial
* Update of enemy cards in hand
* update of enemy played cards
* Extra features:
    * Sign up screen
    * Login/Logout screen
    * Background selection screen
    
<h3>Back-end</h3>
<h4>Implemented</h4>
* replacing begin cards
* Deckbuilder
* Database
* Minion
* Spell
* Weapon
* Abilities:
    * Charge
    * Divine Shield
    * Windfury
* Auto create database
* Turn end
* Event listener for time
* Very basics of auto-player

<h4>Not Yet Implemented</h4>
* CLI
* actual execution of abilities
* Other abilities
* Mechanics
* using the event listener
* Starting your turn after auto-players turn
