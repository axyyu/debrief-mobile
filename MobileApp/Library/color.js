/**
 * Created by andrew on 8/23/17.
 */

var colorDict = {
    "selected":"#56BAFC",
    "science":"#48D296",
    "sports":"#FF8484",
    "politics":"#C47DEC",
    "entertainment":"#5fa0d8",
    "money":"#e570cf",
    "movies":"#62db62",
    "music":"#46d3d6",
    "technology":"#efae58",
    "world":"#ef8757"
};


var hexToRgbA = function (hex){
    var c;
    if(/^#([A-Fa-f0-9]{3}){1,2}$/.test(hex)){
        c= hex.substring(1).split('');
        if(c.length== 3){
            c= [c[0], c[0], c[1], c[1], c[2], c[2]];
        }
        c= '0x'+c.join('');
        return 'rgba('+[(c>>16)&255, (c>>8)&255, c&255].join(',')+',0.1)';
    }
    throw new Error('Bad Hex');
};

module.exports.colorDict = colorDict;
module.exports.hexToRGBA = hexToRgbA;