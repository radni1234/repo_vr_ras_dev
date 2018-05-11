import { BaseEntity } from './../../shared';

export class UgovorIntervencijaStav implements BaseEntity {
    constructor(
        public id?: number,
        public opis?: string,
        public cena?: number,
        public ugovorIntervencija?: BaseEntity,
        public intervencijaTip?: BaseEntity,
    ) {
    }
}
