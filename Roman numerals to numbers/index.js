function converter(inputNumber){
  let romanValues09 = ['I','II','III','IV','V','VI','VII','VIII','IX'];
  let romanValues1050 = ['X','XX','XXX','XL','L'];
  let output = '';
  let arrayString = inputNumber.toString().split('');
  for(i=0;i<arrayString.length;i++){
    if(i==arrayString.length-2){
      if(arrayString[i] != 0){
        output+=romanValues1050[arrayString[i]-1];
      }
    }else{
      if(arrayString[i] != 0){
        output+=romanValues09[arrayString[i]-1];
      }
    }
  }
  return output;
}
module.exports = converter;