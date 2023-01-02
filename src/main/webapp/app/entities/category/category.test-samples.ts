import { Type } from 'app/entities/enumerations/type.model';
import { YesNo } from 'app/entities/enumerations/yes-no.model';

import { ICategory, NewCategory } from './category.model';

export const sampleWithRequiredData: ICategory = {
  id: 2529,
  code: 'Namibia Sausages',
  type: Type['WELLNESS'],
  active: YesNo['Y'],
  name: 'USB Down-sized Principal',
};

export const sampleWithPartialData: ICategory = {
  id: 82331,
  code: 'task-force Garden target',
  type: Type['FITNESS'],
  active: YesNo['N'],
  name: 'Handmade hack Rustic',
};

export const sampleWithFullData: ICategory = {
  id: 72591,
  code: 'Licensed Account',
  type: Type['WELLNESS'],
  active: YesNo['N'],
  name: 'Avon Group',
  label: 'Shoals hub withdrawal',
};

export const sampleWithNewData: NewCategory = {
  code: 'benchmark',
  type: Type['FITNESS'],
  active: YesNo['Y'],
  name: 'Frozen',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
