import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(
    home: Home(),
  ));
}

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  List<DropdownMenuItem<String>> _dropDownMenuItems;
  String _statusSel;
  TextEditingController celsiusController = TextEditingController();
  String _infoText = "Informe a Temperatura em Celsius!";

  GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  void _resetFields() {
    celsiusController.text = "";
    setState(() {
      _infoText = "Informe a Temperatura em Celsius!";
      _statusSel = _getDropDownMenuItems()[0].value;
      //_formKey = GlobalKey<FormState>();
    });
  }

  void _converter(){
    setState(() {
      double celsius = double.parse(celsiusController.text);
      double temperaturaConvertida;

      if(_statusSel == "Kelvin"){
        temperaturaConvertida = celsius + 273.15;
        _infoText = "${temperaturaConvertida.toStringAsPrecision(4)} K";
      } else if (_statusSel == "Fahrenheit"){
        temperaturaConvertida = celsius * 1.8 + 32.0;
        _infoText = "${temperaturaConvertida.toStringAsPrecision(4)} F";
      } else if(_statusSel == "Reamur"){
        temperaturaConvertida = celsius * (4/5);
        _infoText = "${temperaturaConvertida.toStringAsPrecision(4)} °Ré";
      } else if (_statusSel == "Rankine"){
        temperaturaConvertida = celsius * 1.8 + 491.67;
        _infoText = "${temperaturaConvertida.toStringAsPrecision(4)} °R";
      }

      print(temperaturaConvertida);
    });
  }

  List<DropdownMenuItem<String>> _getDropDownMenuItems() {
    List<DropdownMenuItem<String>> items = new List();

    items.add(new DropdownMenuItem(value: 'Kelvin', child: new Text("Kelvin")));

    items.add(new DropdownMenuItem(value: 'Fahrenheit', child: new Text("Fahrenheit")));

    items.add(new DropdownMenuItem(value: 'Reamur', child: new Text("Reaumur")));

    items.add(new DropdownMenuItem(value: 'Rankine', child: new Text("Rankine")));

    return items;
  }

  void changedDropDownItem(String selectedItem) {
    setState(() {
      _statusSel = selectedItem;
    });
  }

  @override
  void initState() {
    _dropDownMenuItems = _getDropDownMenuItems();
    _statusSel = _dropDownMenuItems[0].value;

    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Conversor de Temperatura"),
          centerTitle: true,
          backgroundColor: Colors.orange,
          actions: <Widget>[
            IconButton(
              icon: Icon(Icons.refresh),
              onPressed: _resetFields,
            )
          ],
        ),
        backgroundColor: Colors.white,
        body: SingleChildScrollView(
          padding: EdgeInsets.fromLTRB(15.0, 23.0, 15.0, 0.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              Icon(Icons.wb_sunny, size: 120.0, color: Colors.yellow),
              TextField(
                keyboardType: TextInputType.number,
                decoration: InputDecoration(
                    labelText: "Temperatura (°C)",
                    labelStyle: TextStyle(color: Colors.orange)),
                textAlign: TextAlign.center,
                style: TextStyle(color: Colors.black, fontSize: 20.0),
                controller: celsiusController,
              ),
              Padding(
                padding: EdgeInsets.only(top: 20.0, bottom: 10.0),
                child: Text(
                  "Escalas para a Conversão",
                  //textAlign: TextAlign.center,
                  style: TextStyle(color: Colors.orange, fontSize: 20.0),
                ),
              ),
              new DropdownButton(
                icon: Icon(Icons.arrow_drop_down),
                iconSize: 20,
                elevation: 14,
                style: TextStyle(color: Colors.orange, fontSize: 20.0),
                value: _statusSel,
                items: _dropDownMenuItems,
                onChanged: changedDropDownItem,
              ),
              Padding(
                padding: EdgeInsets.only(top: 10.0, bottom: 15.0),
                child: Container(
                  height: 50.0,
                  child: RaisedButton(
                    onPressed: _converter,
                    child: Text("Converter!",
                        style: TextStyle(color: Colors.white, fontSize: 20.0)),
                    color: Colors.orange,
                  ),
                ),
              ),
              Text(
                _infoText,
                textAlign: TextAlign.center,
                style: TextStyle(color: Colors.orange, fontSize: 20.0),
              )
            ],
          ),
        ));
  }
}
