DOSSEG
.MODEL SMALL
.STACK 100h
.DATA
	HW db "Hello World$"
.CODE
	BEGIN:
		MOV AX, @data
		MOV DS, AX
		MOV ES, AX
		
		LEA DX, HW
		MOV AH, 09h
		INT 21h
		
		MOV AH, 4Ch
		INT 21h
	END BEGIN